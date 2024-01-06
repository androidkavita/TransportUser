package com.govahan.com.activities.passengers.bookingconfirmationstatus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.activities.DashboardActivity
import com.govahan.com.activities.passengers.passengerbookingreview.BookingReviewPActivity
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityBookingConfirmationStatusPactivityBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookingConfirmationStatusPActivity : BaseActivity() {
    private lateinit var binding : ActivityBookingConfirmationStatusPactivityBinding
    var ppaymentMode:String?=null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_confirmation_status_pactivity)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Booking Confirmation & Status")

        binding.btnBack.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()

        })

        if (intent!=null) {
            ppaymentMode = intent.getStringExtra("payment_mode")

            try {
                for (i in 0 until BookingReviewPActivity.bookingPassengerOnlineDataList.size) {
                    binding.bookingid.text =
                        "Booking Id: #" + BookingReviewPActivity.bookingPassengerOnlineDataList[i].bookingId
                    binding.tvBookingCreatedate.text =
                        BookingReviewPActivity.bookingPassengerOnlineDataList[i].bookingDate

                    binding.tvVehicleNumber.text = BookingReviewPActivity.bookingPassengerOnlineDataList[i].vehicleNumbers
                    binding.tvVehicleName.text = BookingReviewPActivity.bookingPassengerOnlineDataList[i].vehicle_name
                    binding.tvFrom.text =
                        BookingReviewPActivity.bookingPassengerOnlineDataList[i].picupLocation
                    binding.tvTo.text =
                        BookingReviewPActivity.bookingPassengerOnlineDataList[i].dropLocation

                    binding.tvRating.text =
                        BookingReviewPActivity.bookingPassengerOnlineDataList[i].rating
                    binding.tvOwnerName.text =
                        BookingReviewPActivity.bookingPassengerOnlineDriverList[i].vehicle_owner_name

                    binding.tvCapacity.text =
                        BookingReviewPActivity.bookingPassengerOnlineDataList[i].seat_no.toString()
                    binding.tvDistance.text =
                        BookingReviewPActivity.bookingPassengerOnlineDataList[i].distance
                    binding.tvBookingdate.text =
                        BookingReviewPActivity.bookingPassengerOnlineDataList[i].bookingDate

                    binding.tvAmount.text =
                        "₹${BookingReviewPActivity.bookingPassengerOnlineDataList[i].fare}"
                    binding.tvPaymentmode.text = "Online"
                    binding.tvBookingStatus.text = "Partial Payment Completed"
                    binding.tvRideCode.text =
                    "${BookingReviewPActivity.bookingPassengerOnlineDataList[i].ride_code}"

                }

                for (i in 0 until BookingReviewPActivity.bookingPassengerOnlineUserList.size) {
                    binding.tvUsreName.text =
                        BookingReviewPActivity.bookingPassengerOnlineUserList[i].name
                    binding.tvUserPhone.text =
                        BookingReviewPActivity.bookingPassengerOnlineUserList[i].mobileNumber
                    binding.tvUsreEmail.text =
                        BookingReviewPActivity.bookingPassengerOnlineUserList[i].email
                }

                for (i in 0 until BookingReviewPActivity.bookingPassengerOnlineDriverList.size) {
                    binding.tvDriverNam.text =
                        BookingReviewPActivity.bookingPassengerOnlineDriverList[i].name
                    binding.tvDrivername.text =
                        BookingReviewPActivity.bookingPassengerOnlineDriverList[i].name
                    binding.tvDriverphone.text =
                        BookingReviewPActivity.bookingPassengerOnlineDriverList[i].mobileNumber
                }


//            }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        }
}