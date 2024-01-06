package com.govahan.com.activities.loaderraisecomplaint

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityRaiseComplaintBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoaderRaiseComplaintActivity : BaseActivity() {
    private lateinit var binding : ActivityRaiseComplaintBinding
    private val viewModel : LoaderRaiseComplaitViewModel by viewModels()

     lateinit var getBookingId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_raise_complaint)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()

        })

        binding.header.tvHeaderText.setText("Raise Complaint")



       /* binding.btnSubmit.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, BookingConfirmationAndStatusActivity::class.java)
            startActivity(intent)

        })
*/


        val getBookingId = intent.getStringExtra("BookingId")


        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }


        viewModel.addLoaderComplaintResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                finish()

            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }


        binding.btnSubmit.setOnClickListener(View.OnClickListener {





            if(binding.etSubject.text.toString().isBlank()){
                toast(this , "Please enter complaint subject.")
            }
            else if(binding.etComplaint.text.toString().isBlank()){
                toast(this , "Please enter your complaint.")
            }
            else{
                viewModel.loaderAddRaiseComplaintApi(
                    "Bearer " + userPref.user.apiToken,getBookingId.toString(),
                    binding.etComplaint.text.toString()
                )
            }





        })









    }
}