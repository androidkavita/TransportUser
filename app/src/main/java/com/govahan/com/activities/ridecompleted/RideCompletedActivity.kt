package com.govahan.com.activities.ridecompleted

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.govahan.com.R
import com.govahan.com.activities.writereview.WriteAreviewActivity
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityRideCompletedBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RideCompletedActivity : BaseActivity() {
    private lateinit var binding : ActivityRideCompletedBinding
    lateinit var saveRating : String
    private val viewModel: RideCompletedViewModel by viewModels()


    lateinit var bookingIdd: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ride_completed)

        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.tvReview.setOnClickListener(View.OnClickListener {

            saveRating = binding.rating.rating.toString()

            val intent = Intent(this, WriteAreviewActivity::class.java)
            intent.putExtra("putRating", saveRating)
            startActivity(intent)
        })


        bookingIdd = intent.getStringExtra("loaderTripBookingId").toString()



        viewModel.loaderRideCompletedResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)

               try{
                   binding.tvPickLocation.text = it.data[0].dropLocation
                   binding.tvDropLocation.text = it.data[0].picupLocation
                   binding.tvAmount.text = "₹" + it.data[0].fare
                   binding.tvPaymentModeType.text = it.data[0].paymentMode

               }
               catch (e:Exception){
                   e.printStackTrace()
               }


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

        viewModel.loaderRideCompletedApi("Bearer " + userPref.user.apiToken, bookingIdd)

        Log.d(TAG, "onCreate: "+bookingIdd)



    }
}