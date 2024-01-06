package com.govahan.com.activities.privacypolicy

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPrivacyPolicyBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivacyPolicyActivity : BaseActivity() {
    private lateinit var binding : ActivityPrivacyPolicyBinding
    private val viewModel : PrivacyPolicyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_privacy_policy)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Privacy Policy")

        viewModel.privacyPolicyApi("Bearer " + userPref.user.apiToken)
        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.privacyPolicyResponse.observe(this) {
            if (it.status == 1) {

                binding.tvPrivacy.text = it.data!!.description

            } else {
                toast(it.message!!)
            }
        }


    }
}