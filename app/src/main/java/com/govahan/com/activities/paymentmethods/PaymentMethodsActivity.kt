package com.govahan.com.activities.paymentmethods

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPaymentMethodsBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentMethodsActivity : BaseActivity() {
    private lateinit var binding : ActivityPaymentMethodsBinding
    lateinit var choosenPaymentMode : String
    lateinit var choosenDrawable : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_methods)

        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        val radG = findViewById<View>(R.id.radioGroup) as RadioGroup
        radG.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                // checkedId is the RadioButton selected
                when (checkedId) {
                    R.id.radio_googlepay -> {
                       // toast("googlepay")
                        choosenPaymentMode = "google Pay"
                      //  choosenDrawable = mkmkkm



                        toast(choosenPaymentMode)
                    }
                    R.id.radio_wallet -> {
                      //  toast("wallet")
                        choosenPaymentMode = "Wallet"
                        toast(choosenPaymentMode)
                    }
                    R.id.radio_upi -> {
                      //  toast("upi")
                        choosenPaymentMode = "UPI"
                        toast(choosenPaymentMode)
                    }
                    R.id.radio_paytm -> {
                      //  toast("paytm")
                        choosenPaymentMode = "Paytm"
                        toast(choosenPaymentMode)
                    }
                    R.id.radio_creditdebit -> {
                      //  toast("creditdebit")
                        choosenPaymentMode = "Credit Debit"
                        toast(choosenPaymentMode)
                    }
                    R.id.radio_cash -> {
                        choosenPaymentMode = "Cash"
                        toast(choosenPaymentMode)

                    }
                }
            }
        })


        binding.btnSelect.setOnClickListener(View.OnClickListener {
                val intent = Intent()
                intent.putExtra("choosenPaymentMode", choosenPaymentMode)
                setResult(RESULT_OK,intent)
                finish()
        })

    }
}