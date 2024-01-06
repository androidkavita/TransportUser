package com.govahan.com.activities.loaderWalletAddMoney

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityLoaderWalletAddMoneyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoaderWalletAddMoneyActivity : BaseActivity() {
    private lateinit var binding : ActivityLoaderWalletAddMoneyBinding
    private val viewModel : LoaderWalletAddMoneyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader_wallet_add_money)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        binding.header.tvHeaderText.setText("Add Money")

        /*viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }*/

      /*  viewModel.addMoneyWalletResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)

            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }*/

        binding.btnAdd.setOnClickListener {
            /*if(binding.edtAmount.text.toString().isBlank()){
                toast(this , "Please enter amount.")
            }
            else{
                viewModel.addFavouriteLocationApi("Bearer " + userPref.user.apiToken,
                    binding.edtAmount.text.toString(),
                )
            }*/
        }


    }
}