package com.govahan.com.activities.contactus

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityContactUsBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactUsActivity : BaseActivity() {
    private lateinit var binding : ActivityContactUsBinding
    private val viewModel : ContactUsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Contact Us")


        viewModel.contactUsApi("Bearer " + userPref.user.apiToken)

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.contactUsResponse.observe(this) {
            if (it.status == 1) {

                binding.tvAddress.text = it.data!!.address
                binding.tvPhone.text = it.data!!.contactNumber
                binding.tvMail.text = it.data!!.email

                Glide.with(this).load(it.data!!.image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_person))
                    .apply(RequestOptions.errorOf(R.drawable.ic_person))
                    .into(binding.ivImg)


            } else {
                toast( it.message!!)
            }
        }


    }
}