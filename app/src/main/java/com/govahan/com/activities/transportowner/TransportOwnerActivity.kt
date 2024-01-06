package com.govahan.com.activities.transportowner

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.adapters.ReviewsAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityTransportOwnerBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransportOwnerActivity : BaseActivity() {
    private lateinit var binding : ActivityTransportOwnerBinding
    private val viewModel : TransportOwnerViewModel by viewModels()
    private var reviewsAdapter : ReviewsAdapter ?= null

    var driveriddd = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transport_owner)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Transport Owner")

//        val layoutManagerTopSongs: RecyclerView.LayoutManager = LinearLayoutManager(this,
//            GridLayoutManager.VERTICAL, false)
//        binding.rvReviews.setLayoutManager(layoutManagerTopSongs)
//        val itemsTopSongs: List<String> = Arrays.asList("item", "item", "item", "item", "item", "item", "item", "item", "item", "item")
//        binding.rvReviews.setAdapter(ReviewsAdapter(this, itemsTopSongs))
        driveriddd = intent.getStringExtra("putdriveridd").toString()
        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.transportOwnerResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)
                binding.tvOwnername.text = it.data[0].name
                binding.tvOwnerMobile.text =  it.data[0].mobileNumber
                binding.tvTransporterLoads.text = it.data[0].transporterLoads
                binding.tvCompletedTrips.text = it.data[0].totalCompletedTrips
            //  Glide.with(this).load(selectedVehicleData?.mainImage).into(binding.ivVehicleImage)
            } else {
                toast(it.message!!)
            }
        }
        viewModel.owner_driverDetailApi("Bearer " + userPref.user.apiToken, driveriddd)




























        viewModel.getRatingApi("Bearer " + userPref.user.apiToken,
            userPref.getDriverId().toString())



        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }
        viewModel.getRatingResponse.observe(this) {
            if (it.status == 1) {
                reviewsAdapter =  ReviewsAdapter(it.data)

                binding.rvReviews.apply {
                    adapter = reviewsAdapter
                    layoutManager = LinearLayoutManager(this@TransportOwnerActivity)
                }

            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }












    }
}