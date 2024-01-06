package com.govahan.com.activities.passengers.passengerRideCompleted

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.govahan.com.R
import com.govahan.com.activities.passengers.passengerwritereview.PassengerWriteAReviewActivity
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPassengerRideCompletedBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassengerRideCompletedActivity : BaseActivity() {
    private lateinit var binding : ActivityPassengerRideCompletedBinding
    lateinit var saveRating : String
    private val viewModel: PassengerRideCompletedViewModel by viewModels()
    lateinit var bookingIdd: String
    lateinit var userIdd: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_ride_completed)
        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        binding.tvReview.setOnClickListener(View.OnClickListener {
            saveRating = binding.rating.rating.toString()
            val intent = Intent(this, PassengerWriteAReviewActivity::class.java)
            intent.putExtra("putRating", saveRating)
            intent.putExtra("putUserId", userIdd)
            startActivity(intent)
        })
        bookingIdd = intent.getStringExtra("passengerTripBookingId").toString()
        userIdd = intent.getStringExtra("passengerTripUserId").toString()

        viewModel.passengerRideCompletedResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)

                binding.tvPickLocation.text = it.data[0].dropLocation
                binding.tvDropLocation.text = it.data[0].picupLocation
                binding.tvAmount.text = "₹" + it.data[0].fare
                binding.tvPaymentModeType.text = it.data[0].paymentMode
                if(it.data[0].paymentMode.equals("1"))
                {binding.tvPaymentModeType.setText("Cash")}
                else  if(it.data[0].paymentMode.equals("2"))
                {binding.tvPaymentModeType.setText("Online")}
                Glide.with(this).load(it.data[0].profileImage).into(binding.imgUser)

                // userPref.setDriverId(it.data[0]!!.driverId.toString())

            } else {
                toast(it.message!!)
            }
        }

        viewModel.passengerRideCompletedApi("Bearer " + userPref.user.apiToken, bookingIdd)

         Log.d(ContentValues.TAG, "onCreate: "+bookingIdd)

    }
}