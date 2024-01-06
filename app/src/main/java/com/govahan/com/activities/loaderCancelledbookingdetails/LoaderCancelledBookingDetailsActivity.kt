package com.govahan.com.activities.loaderCancelledbookingdetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityLoaderCancelledBookingDetailsBinding
import com.govahan.com.model.cancelledloadertriphistorymodel.CancelledLoaderTripHistoryData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoaderCancelledBookingDetailsActivity : BaseActivity() {
    private lateinit var binding : ActivityLoaderCancelledBookingDetailsBinding
    private var selectedLoaderCancelledHistoryData: CancelledLoaderTripHistoryData? = null
    private val viewModel: LoaderCancelledBookingDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader_cancelled_booking_details)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Booking Details")


        val data = intent.extras
        selectedLoaderCancelledHistoryData = data?.getParcelable<CancelledLoaderTripHistoryData>("loaderCancelledHistoryDetails")

        Log.d("TAG___", "onCreate: " + selectedLoaderCancelledHistoryData!!.bookingId.toString())

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.loaderCancelledHistoryDetailResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message)
                try {
                    binding.tvPaid.text = it.data[0].fare
                    //  binding.tvPaymentMethod.text = it.data[0].payment_mode

                    binding.tvBookingId.text = it.data[0].booking_id
                    binding.tvTripStatus.text = it.data[0].booking_status
                    binding.tvDate.text = it.data[0].booking_date
                    binding.tvTime.text = it.data[0].booking_time
                    binding.tvPickup.text = it.data[0].picup_location
                    binding.tvDropoff.text = it.data[0].drop_location
                    binding.tvVehicleType.text = it.data[0].vehicle_name
                    binding.tvBodyType.text = it.data[0].body_type
                    binding.tvVehicleNumber.text = it.data[0].vehicle_numbers
                    binding.tvTotalLoads.text = it.data[0].booking_time
                    binding.tvPartyName.text = it.data[0].booking_time


                    if (it.data[0].payment_mode.equals("1")) {
                        binding.tvPaymentMethod.setText("Cash")
                    } else if (it.data[0].payment_mode.equals("2")) {
                        binding.tvPaymentMethod.setText("Online")
                    }

                    binding.tvUseremail.text = it.userDetails.email
                    binding.tvUsername.text = it.userDetails.name
                    binding.tvUserphone.text = it.userDetails.mobile_number

                }catch (e:Exception){
                    e.printStackTrace()
                }

                /*if(it.data[0].payment_mode.equals("1"))
                {binding.tvPaymentMethod.setText("Cash")}
                else  if(it.data[0].payment_mode.equals("2"))
                {binding.tvPaymentMethod.setText("Online")}*/

            } else {
                toast(it.message!!)
            }
        }

        viewModel.loaderOngoingHistoryDetailApi(
            "Bearer " + userPref.user.apiToken,
            selectedLoaderCancelledHistoryData?.bookingId!!
        )

       /* Log.d(TAG, "onCreate9999: "+userPref.user.apiToken+"9999999999"+
            selectedLoaderCancelledHistoryData?.bookingId!!)
*/

        /*binding.btnRaiseComplaint.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoaderRaiseComplaintActivity::class.java)
            intent.putExtra("BookingId", selectedLoaderCancelledHistoryData?.bookingId!!.toString())
            startActivity(intent)
        })*/


    }
}