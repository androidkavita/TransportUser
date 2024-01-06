package com.govahan.com.activities.aboutus

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.activities.privacypolicy.PrivacyPolicyViewModel
import com.govahan.com.databinding.ActivityAboutUsBinding
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityTermsConditionsBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutUsActivity : BaseActivity() {
    private lateinit var binding : ActivityAboutUsBinding
    private val viewModel : PrivacyPolicyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_us)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("About Us")

        viewModel.aboutUs("Bearer " + userPref.user.apiToken)
        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.privacyPolicyResponse.observe(this) {
            if (it.status == 1) {

                binding.aboutus.text = it.data!!.description

            } else {
                toast(it.message!!)
            }
        }
    }
}