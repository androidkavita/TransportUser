package com.govahan.com.activities.loadertracktruckdriver

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityLoaderTrackTruckDriverBinding
import com.govahan.com.util.DateFormat
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoaderTrackTruckDriverActivity : BaseActivity() {
    private lateinit var binding : ActivityLoaderTrackTruckDriverBinding
    private val viewModel : LoaderTrackTruckdriverViewModel by viewModels()
   // lateinit var getBookingId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader_track_truck_driver)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Track Truck Driver")

        val getBookingId = intent.getStringExtra("BookingId")

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.loaderLiveTrackingResponseModel.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
               // finish()
                binding.tvTruckName.text = it.data.booking_details[0].vehicle_name
                binding.tvWheeler.text = "${it.data.booking_details[0].capacity} Tons"
                binding.tvTruckNumber.text = it.data.booking_details[0].vehicle_numbers
                binding.tvDriverName.text = it.data.booking_details[0].driver_name
                binding.tvDriverType.text = it.data.booking_details[0].driver_name
                binding.tvDrivePhone.text = it.data.booking_details[0].mobile
                binding.tvFrom.text = it.data.booking_details[0].picup_location
                binding.tvFromdate.text = DateFormat.TimeFormat(it.data.booking_details[0].booking_date)
                binding.tvTo.text = it.data.booking_details[0].drop_location
                binding.tvTodate.text = DateFormat.TimeFormat(it.data.booking_details[0].booking_date)
                binding.tvTripStartedDate.text = "${it.expected_deliver_date.date}(${it.expected_deliver_date.time})"
                binding.tvTimeLeft.text = DateFormat.TimeFormat(it.data.booking_details[0].booking_date) + " left to reach destination"
                Glide.with(this).load(it.data.booking_details[0].vehicle_image).into(binding.ivTruck)
                Glide.with(this).load(it.data.booking_details[0].driver_image).into(binding.ivDriver)

                //     1=>pending, 2=>accepted, 3=>cancel, 4=> Completed

                if(it.data.booking_details[0].booking_status.toString().equals("1"))
                {
                    binding.tvStatus1.text = "Trip not started."
                    binding.tvStatus1.setTextColor(resources.getColor(R.color.theme_yellow))
                    binding.ivDot1.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.icon_tracking_dot1))
                    binding.vw1.setBackgroundColor(Color.parseColor("#eb8900"))

                }
                else if(it.data.booking_details[0].booking_status.toString().equals("2"))
                {
                    binding.tvStatus1.setTextColor(resources.getColor(R.color.theme_yellow))
                    binding.tvStatus2.setTextColor(resources.getColor(R.color.theme_yellow))
                    binding.ivDot1.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.icon_tracking_dot1))
                    binding.ivDot2.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.icon_tracking_dot1))
                    binding.vw1.setBackgroundColor(Color.parseColor("#eb8900"))
                    binding.vw2.setBackgroundColor(Color.parseColor("#eb8900"))
                }
                else if(it.data.booking_details[0].booking_status.toString().equals("4"))
                {
                    binding.tvStatus1.setTextColor(resources.getColor(R.color.theme_yellow))
                    binding.tvStatus2.setTextColor(resources.getColor(R.color.theme_yellow))
                    binding.tvStatus3.setTextColor(resources.getColor(R.color.theme_yellow))
                    binding.ivDot1.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.icon_tracking_dot1))
                    binding.ivDot2.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.icon_tracking_dot1))
                    binding.ivDot3.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.icon_tracking_dot1))
                    binding.vw1.setBackgroundColor(Color.parseColor("#eb8900"))
                    binding.vw2.setBackgroundColor(Color.parseColor("#eb8900"))
                    binding.vw3.setBackgroundColor(Color.parseColor("#eb8900"))
                }


            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }



        viewModel.loaderLiveTrackingApi(
            "Bearer " + userPref.user.apiToken,getBookingId.toString()
        )

        Log.d(TAG, "onCreate: " + getBookingId.toString())

    }
}