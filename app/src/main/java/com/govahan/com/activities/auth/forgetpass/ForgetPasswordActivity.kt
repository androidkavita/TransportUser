package com.govahan.com.activities.auth.forgetpass

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityForgetPasswordBinding


class ForgetPasswordActivity : BaseActivity() {
    private lateinit var binding : ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password)

        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()

        })

    }
}