package com.govahan.com.activities.passengers.passengerwritereview

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPassengerWriteAreviewBinding
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PassengerWriteAReviewActivity : BaseActivity() {
    private lateinit var binding : ActivityPassengerWriteAreviewBinding
    var getRating = " "
    var getUserIdd = " "
    private val viewModel : PassengerWriteAReviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_write_areview)

        binding.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        getRating = intent.getStringExtra("putRating").toString()
        getUserIdd = intent.getStringExtra("putUserId").toString()
        //  toast(getRating)
        binding.rating.setRating(getRating.toFloat());

        binding.btnSubmit.setOnClickListener {
            viewModel.passengerAddRatingApi("Bearer " + userPref.user.apiToken ,
                userPref.getDriverId().toString(),

                getRating,
                binding.etFeedback.text.trim().toString(),getUserIdd

            )

            Log.d(TAG, "onCreate:userid " +getUserIdd)

        }

        viewModel.progressBarStatus.observe(this, androidx.lifecycle.Observer {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        })

        viewModel.passengerAddRatingResponse.observe(this, androidx.lifecycle.Observer {
            if (it.status == 1) {

                toast(it.message!!)
                finish()
            } else {
                hideProgressDialog()
                toast(it.message!!)
            }
        })


    }
}