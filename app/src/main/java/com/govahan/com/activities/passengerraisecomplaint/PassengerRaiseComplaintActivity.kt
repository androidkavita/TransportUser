package com.govahan.com.activities.passengerraisecomplaint

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPassengerRaiseComplaintBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassengerRaiseComplaintActivity : BaseActivity() {

    private lateinit var binding : ActivityPassengerRaiseComplaintBinding
    private val viewModel : PassengerRaiseComplaitViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_raise_complaint)
        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        binding.header.tvHeaderText.setText("Raise Complaint")








        val getBookingId = intent.getStringExtra("BookingId")


        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }


        viewModel.addPassengerComplaintResponse.observe(this) {
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
                viewModel.passengerAddRaiseComplaintApi(
                    "Bearer " + userPref.user.apiToken,getBookingId.toString(),
                    binding.etComplaint.text.toString()
                )
            }





        })









    }
}