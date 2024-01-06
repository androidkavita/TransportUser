package com.govahan.com.activities.settings

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivitySettingsBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import it.beppi.tristatetogglebutton_library.TriStateToggleButton

@AndroidEntryPoint
class SettingsActivity : BaseActivity() {
    private lateinit var binding : ActivitySettingsBinding
    private val viewModel : SettingsActivityViewModel by viewModels()
    lateinit var getWhatsappStatus: String
    lateinit var getSmsEmailStatus: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Setting")



        getWhatsappStatus = userPref.getWhatsappStatus().toString()
        getSmsEmailStatus = userPref.getSmsEmailStatus().toString()
        Log.d(TAG, "onCreate__:W def"+getWhatsappStatus +"onCreate__:S def"+getSmsEmailStatus )






             if (getWhatsappStatus== "1") {
                        binding.tbWhatsappupdates.setToggleOn()
                    } else {
                        binding.tbWhatsappupdates.setToggleOff()
                    }

            if (getSmsEmailStatus== "1") {
                binding.tbSmsemail.setToggleOn()
            } else {
                binding.tbSmsemail.setToggleOff()
            }


              binding.tbWhatsappupdates.setOnToggleChanged(TriStateToggleButton.OnToggleChanged { toggleStatus, booleanToggleStatus, toggleIntValue ->
            when (toggleStatus) {
                TriStateToggleButton.ToggleStatus.off -> {
                   // changeServiceStatus("0")
                    toast("2")
                    getWhatsappStatus = "2"
                    viewModel.settingsWhatsappUpdatesApi("Bearer " + userPref.user.apiToken,getWhatsappStatus)
                    Log.d(TAG, "onCreate__:W off "+getWhatsappStatus)

                }
                TriStateToggleButton.ToggleStatus.mid -> {

                }
                TriStateToggleButton.ToggleStatus.on -> {
                  //  changeServiceStatus("1")
                    toast("1")
                    getWhatsappStatus = "1"
                    viewModel.settingsWhatsappUpdatesApi("Bearer " + userPref.user.apiToken,getWhatsappStatus)
                    Log.d(TAG, "onCreate__:W on "+getWhatsappStatus)
                }
            }
        })


        binding.tbSmsemail.setOnToggleChanged(TriStateToggleButton.OnToggleChanged { toggleStatus, booleanToggleStatus, toggleIntValue ->
            when (toggleStatus) {
                TriStateToggleButton.ToggleStatus.off -> {
                    // changeServiceStatus("0")
                    toast("2")
                    getSmsEmailStatus = "2"
                    viewModel.settingsSmsEmailUpdatesApi("Bearer " + userPref.user.apiToken,getSmsEmailStatus)
                    Log.d(TAG, "onCreate__:S off "+getSmsEmailStatus)
                }
                TriStateToggleButton.ToggleStatus.mid -> {

                }
                TriStateToggleButton.ToggleStatus.on -> {
                    //  changeServiceStatus("1")
                    toast("1")
                    getSmsEmailStatus = "1"
                    viewModel.settingsSmsEmailUpdatesApi("Bearer " + userPref.user.apiToken,getSmsEmailStatus)
                    Log.d(TAG, "onCreate__:S off "+getSmsEmailStatus)
                }
            }
        })


        viewModel.whatsappUpdatesResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                //finish()
                userPref.setWhatsappStatus(it.data)

            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }


        viewModel.smsEmailUpdatesResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                userPref.setSmsEmailStatus(it.data)
                //finish()

            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }








    }
}