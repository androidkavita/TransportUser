package com.govahan.com.activities.passengers.paymentmethods

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPaymentMethodsPactivityBinding

class PaymentMethodsPActivity : BaseActivity() {
    private lateinit var binding : ActivityPaymentMethodsPactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_methods_pactivity)

        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()

        })

    }
}