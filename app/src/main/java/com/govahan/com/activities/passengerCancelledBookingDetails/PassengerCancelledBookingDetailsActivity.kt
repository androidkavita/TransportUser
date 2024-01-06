package com.govahan.com.activities.passengerCancelledBookingDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPassengerCancelledBookingDetailsBinding
import com.govahan.com.model.cancelledpassengertriphistorymodel.CancelledPassengerTripHistoryData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassengerCancelledBookingDetailsActivity : BaseActivity() {
    private lateinit var binding : ActivityPassengerCancelledBookingDetailsBinding
    private var selectedPassengerCancelledHistoryData: CancelledPassengerTripHistoryData? = null
    private val viewModel: PassengerCancelledBookingDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_cancelled_booking_details)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Booking Details")


        val data = intent.extras
        selectedPassengerCancelledHistoryData = data?.getParcelable<CancelledPassengerTripHistoryData>("passengerCancelledHistoryDetails")

        Log.d("TAG___", "onCreate: " + selectedPassengerCancelledHistoryData!!.bookingId.toString())

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.passengerCancelledHistoryDetailResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)

                binding.tvBookingId.text = it.data[0].bookingId
                binding.tvTripStatus.text = it.data[0].bookingStatus
                binding.tvPaid.text = "₹${it.data[0].fare}"

                if(it.data[0].paymentMode.equals("1"))
                {binding.tvPaymentMethod.setText("Cash")}
                else  if(it.data[0].paymentMode.equals("2"))
                {binding.tvPaymentMethod.setText("Online")}



                binding.tvBookingId.text = it.data[0].bookingId
                binding.tvTripStatus.text = it.data[0].bookingStatus
                binding.tvDate.text = it.data[0].bookingDate
                binding.tvTime.text = it.data[0].bookingTime
                binding.tvPickup.text = it.data[0].picupLocation
                binding.tvDropoff.text = it.data[0].dropLocation
                binding.tvVehicleType.text = it.data[0].vehicleName
                binding.tvBodyType.text = it.data[0].bodyType
                binding.tvVehicleNumber.text = it.data[0].vehicleNumber
                binding.tvTotalLoads.text = it.data[0].vehicleNumber
                binding.tvPartyName.text = it.data[0].vehicleNumber
                binding.tvUsername.text=it.userDetails?.name
                binding.tvUseremail.text=it.userDetails?.email
                binding.tvUserphone.text=it.userDetails?.mobileNumber

            } else {
                toast(it.message!!)
            }
        }

        viewModel.passengerOngoingHistoryDetailApi(
            "Bearer " + userPref.user.apiToken,
            selectedPassengerCancelledHistoryData?.bookingId!!
        )

    }

}

