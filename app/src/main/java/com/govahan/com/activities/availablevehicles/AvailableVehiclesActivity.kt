package com.govahan.com.activities.availablevehicles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.activities.ReviewsActivity
import com.govahan.com.activities.bookingreview.BookingReviewActivity
import com.govahan.com.adapters.AvailableVehiclesAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityAvailableVehiclesBinding
import com.govahan.com.model.searchvehiclemodel.SearchVehicleData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AvailableVehiclesActivity : BaseActivity(), AvailableVehiclesAdapter.OnClick {
    private lateinit var binding : ActivityAvailableVehiclesBinding
    private var availableVehiclesAdapter : AvailableVehiclesAdapter ?= null
    private val viewModel : AvailableVehiclesViewModel by viewModels()
    var g_triptask = ""
    var g_pickupLocation = ""
    var pickupLat = ""
    var pickupLong = ""
    var dropLat = ""
    var dropLong = ""

    var g_dropLocation = ""
    var g_truckType = ""
    var g_capacity = ""
    var g_body_type = ""
    var g_wheel = ""
    var g_price_for = ""
    var g_booking_date = ""
    var g_booking_time = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_available_vehicles)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        binding.header.tvHeaderText.setText("Available Vehicles")

        pickupLat = intent.getStringExtra("pickupLatitude")!!
        pickupLong = intent.getStringExtra("pickupLongitude")!!
        dropLat = intent.getStringExtra("dropLatitude")!!
        dropLong = intent.getStringExtra("dropLongitude")!!

        g_triptask = intent.getStringExtra("triptask")!!
        g_pickupLocation = intent.getStringExtra("pickupLocation")!!
        g_dropLocation = intent.getStringExtra("dropLocation")!!
        g_truckType = intent.getStringExtra("truckType")!!
        g_capacity = intent.getStringExtra("capacity")!!
        g_body_type = intent.getStringExtra("body_type")!!
        g_wheel = intent.getStringExtra("wheel")!!
        g_price_for = intent.getStringExtra("price_for")!!
        g_booking_date = intent.getStringExtra("booking_date")!!
        g_booking_time = intent.getStringExtra("booking_time")!!


        viewModel.searchLoaderVehicleApi("Bearer " + userPref.user.apiToken,
            intent.getStringExtra("triptask")!!,
            intent.getStringExtra("pickupLocation")!!/*"28.6068"*/,
            intent.getStringExtra("pickupLatitude")!!/*"28.6068"*/,
            intent.getStringExtra("pickupLongitude")!!/*"77.3218"*/,
            intent.getStringExtra("dropLocation")!!,
            intent.getStringExtra("dropLatitude")!!/*"28.7041"*/,
            intent.getStringExtra("dropLongitude")!!/*"77.1025"*/,
            intent.getStringExtra("truckType")!!,
            intent.getStringExtra("capacity")!!,
            intent.getStringExtra("body_type")!!,
            intent.getStringExtra("wheel")!!,
            intent.getStringExtra("booking_date")!!,
            intent.getStringExtra("booking_time")!!
           )



        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }
        viewModel.availableVehicleListResponse.observe(this) {
            if (it.status == 1) {



                availableVehiclesAdapter =  AvailableVehiclesAdapter(it.data,
                    this,this)



                binding.rvAvailableVehicles.apply {
                    adapter = availableVehiclesAdapter
                    layoutManager = LinearLayoutManager(this@AvailableVehiclesActivity)
                }

            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }


    }


    override fun onCallNowClicked(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    override fun onProceedClicked(searchListModelData: SearchVehicleData) {
        startActivity(Intent(this, BookingReviewActivity :: class.java).also {
            it.putExtra("vehicleDetails", searchListModelData)
            it.putExtra("pickupLatitude", pickupLat)
            it.putExtra("pickupLongitude", pickupLong)
            it.putExtra("dropLatitude", dropLat)
            it.putExtra("dropLongitude", dropLong)
            it.putExtra("dropLongitude", dropLong)
            it.putExtra("g_booking_date", g_booking_date)


        })
    }

    override fun reviewsclick(id: String) {
        startActivity(Intent(this, ReviewsActivity :: class.java).also {
            it.putExtra("driver_id", id)
    })
    }


}