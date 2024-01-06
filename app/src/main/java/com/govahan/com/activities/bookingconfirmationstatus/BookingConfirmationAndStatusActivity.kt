package com.govahan.com.activities.bookingconfirmationstatus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.govahan.com.R
import com.govahan.com.activities.DashboardActivity
import com.govahan.com.activities.bookingreview.BookingReviewActivity
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityBookingConfirmationAndStatusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingConfirmationAndStatusActivity : BaseActivity() {
    private lateinit var binding : ActivityBookingConfirmationAndStatusBinding
    var paymentMode:String?=null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_confirmation_and_status)
        binding.header.tvHeaderText.text = "Confirmation"
        if (intent!=null){
            paymentMode=intent.getStringExtra("payment_mode")
                if (paymentMode.equals("online")){
                for (i in 0 until  BookingReviewActivity.bookingLoaderOnlineDataList.size){
                    binding.tvRideCode.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].ride_code
                    binding.bookingid.text = "Booking Id: #"+ BookingReviewActivity.bookingLoaderOnlineDataList[i].bookingId
                    binding.tvBookingCreatedate.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].createdAt
                    binding.tvFrom.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].picupLocation
                    binding.tvTo.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].dropLocation
                    binding.tvVehicleNumber.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].vehicleNumbers
                    binding.tvRating.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].rating
                    binding.tvType.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].bodyType
                    binding.tvCapacity.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].capacity
                    binding.tvDistance.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].distance
                    binding.tvOwner.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].vehicleOwnerName
                    binding.tvBookingdate.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].bookingDate
                    binding.tvBookingtime.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].bookingTime
                    binding.tvVehicleName.text = BookingReviewActivity.bookingLoaderOnlineDataList[i].vehicle_name
                    binding.tvAmount.text = "₹${BookingReviewActivity.bookingLoaderOnlineDataList[i].fare}"
                    binding.tvPaymentmode.text = "Online"
                    binding.tvBookingStatus.text = "Partial Payment Completed"
                    Glide.with(this).load(BookingReviewActivity.bookingLoaderOnlineDataList[i].image).into(binding.vehicleImage)
                }
                for (i in 0 until  BookingReviewActivity.bookingLoaderOnlineUserList.size) {
                    binding.tvUsreName.text = BookingReviewActivity.bookingLoaderOnlineUserList[i].name
                    binding.tvUserPhone.text = BookingReviewActivity.bookingLoaderOnlineUserList[i].mobileNumber
                    binding.tvUserEmail.text = BookingReviewActivity.bookingLoaderOnlineUserList[i].email
                }
                for (i in 0 until  BookingReviewActivity.bookingLoaderOnlineDriverList.size) {
                    binding.tvDriverNam.text = BookingReviewActivity.bookingLoaderOnlineDriverList[i].name
                    binding.tvDrivername.text = BookingReviewActivity.bookingLoaderOnlineDriverList[i].name
                    binding.tvDriverphone.text = BookingReviewActivity.bookingLoaderOnlineDriverList[i].mobileNumber
                }
            }
        }
        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        binding.header.tvHeaderText.setText("Booking Confirmation & Status")


        binding.btnBack.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}