package com.govahan.com.activities.passengerOngoingBookingDetails

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.CheckBox
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.govahan.com.R
import com.govahan.com.activities.passengerraisecomplaint.PassengerRaiseComplaintActivity
import com.govahan.com.activities.passengertracktruckdriver.PassengerTrackTruckDriverActivity
import com.govahan.com.adapters.PassengerCancelTripReasonAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPassengerOngoingBookingDetailsBinding
import com.govahan.com.databinding.BottomSheetCancelTripBinding
import com.govahan.com.databinding.BottomSheetRidecancellationBinding
import com.govahan.com.model.ongoingPassengerTripHistoryModel.OngoingPassengerHistoryData
import com.govahan.com.model.passengercancelreasonmodel.PassengerCancelReasonData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PassengerOngoingBookingDetailsActivity : BaseActivity() {


    private lateinit var binding : ActivityPassengerOngoingBookingDetailsBinding
    private var selectedPassengerOngoingHistoryData: OngoingPassengerHistoryData? = null
    private val viewModel: PassengerOngoingBookingDetailsViewModel by viewModels()


    private var listData: ArrayList<PassengerCancelReasonData> = ArrayList()
    private var passengerCancelReasonAdapter: PassengerCancelTripReasonAdapter? = null
    var crnNumber = ""
    private var listReasonType_id:ArrayList<String> = ArrayList()
    var reasontypevalue_id: String? = ""
    private lateinit var str: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_ongoing_booking_details)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        binding.header.tvHeaderText.setText("Booking Details")

        binding.btnTracktruck.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,PassengerTrackTruckDriverActivity::class.java)
            intent.putExtra("BookingId", selectedPassengerOngoingHistoryData?.bookingId!!.toString())
            startActivity(intent)
        })

        binding.btnRaiseComplaint.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, PassengerRaiseComplaintActivity::class.java)
            intent.putExtra("BookingId", selectedPassengerOngoingHistoryData?.bookingId!!.toString())
            startActivity(intent)
        })





        val data = intent.extras
        selectedPassengerOngoingHistoryData = data?.getParcelable<OngoingPassengerHistoryData>("passengerOngoingHistoryDetails")

        Log.d("TAG___", "onCreate: " + selectedPassengerOngoingHistoryData!!.bookingId.toString())

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.passengerOngoingHistoryDetailResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)

                binding.tvBookingId.text = it.data[0].bookingId
                binding.tvTripStatus.text = it.data[0].bookingStatus
                binding.tvTotalfare.text = it.data[0].fare

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


                /*if (it.data[0].paymentMode.equals("1")) {
                    binding.tvPaymentMethod.setText("Cash")
                } else if (it.data[0].paymentMode.equals("2")) {
                    binding.tvPaymentMethod.setText("Online")
                }*/

                /*binding.tvUseremail.text = it.userDetails.email
                binding.tvUsername.text = it.userDetails.name
                binding.tvUserphone.text = it.userDetails.mobile_number*/

/*

                binding.tvDate.text = it.data[0].bookingDate
                binding.tvTime.text = it.data[0].bookingTime
                binding.tvPickup.text = it.data[0].picupLocation
                binding.tvDropoff.text = it.data[0].dropLocation
                binding.tvVehicleType.text = it.data[0].vehicleName
                binding.tvBodyType.text = it.data[0].bodyType
                binding.tvVehicleNumber.text = it.data[0].vehicleNumber
                binding.tvTotalLoads.text = it.data[0].vehicleNumber
                binding.tvPartyName.text = it.data[0].vehicleNumber
                binding.tvPartyNumber.text = it.data[0].vehicleNumber


                binding.tvDriverName.text = it.ownerDetails!!.name
                binding.tvDriverPhone.text = it.ownerDetails!!.mobile
                // binding.tvRidesNumber.text = it.data[0].r
                binding.tvUsername.text = it.userDetails!!.name
                binding.tvUserphone.text = it.userDetails!!.mobileNumber
                binding.tvUseremail.text = it.userDetails!!.email
*/

                //  userPref.setDriverId(it.data[0]!!.driverId.toString())

            } else {
                toast(it.message!!)
            }
        }




        viewModel.passengerOngoingHistoryDetailApi(
            "Bearer " + userPref.user.apiToken,
            selectedPassengerOngoingHistoryData?.bookingId!!
        )



        viewModel.getPassengerCancelReasonListApi("Bearer " + userPref.user.apiToken)









        binding.btnCanceltrip.setOnClickListener(View.OnClickListener {

            cancelReasonDialog()


        })


    }




    private fun cancelReasonDialog() {
        val cDialog = Dialog(this, R.style.Theme_Tasker_Dialog)
        val bindingDialog: BottomSheetCancelTripBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.bottom_sheet_cancel_trip,
            null,
            false
        )
        cDialog.setContentView(bindingDialog.root)

        cDialog.setCancelable(false)
        cDialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        cDialog.show()

        viewModel.passengerCancelReasonResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                listData.clear()
                listData.addAll(it.data)
                passengerCancelReasonAdapter =
                    PassengerCancelTripReasonAdapter(this, listData)
                bindingDialog.rvReasons.apply {
                    adapter = passengerCancelReasonAdapter
                    layoutManager = LinearLayoutManager(context)

                }
            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }








        viewModel.passengerTripCancelResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                crnNumber = it.CRN.toString()
                cDialog.dismiss()
                rideCancelledDialog()

                //   finish()
            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }


        bindingDialog.btnConfirmCancel.setOnClickListener {
            // cDialog.dismiss()

            listReasonType_id.clear()
            bindingDialog.etFeedback.text.toString()
            for (i in 0 until bindingDialog.rvReasons.childCount){
                val cbReason =bindingDialog.rvReasons.getChildAt(i).findViewById(R.id.cb_reason) as CheckBox
                if (cbReason.isChecked){
                    val id=listData[i].id
                    listReasonType_id.add(id.toString())

                    reasontypevalue_id =  listReasonType_id.toString()
                    str= android.text.TextUtils.join(",", listReasonType_id)
                    //   datetypevalue_id = android.text.TextUtils.join(",", listDateType_id);

                }
            }


            viewModel.passengerTripCancelApi(
                "Bearer " + userPref.user.apiToken,
                selectedPassengerOngoingHistoryData?.bookingId!!,
                str,
                bindingDialog.etFeedback.text.toString()
            )
            Log.d("CheckBoxInfo",   selectedPassengerOngoingHistoryData?.bookingId!!+str+bindingDialog.etFeedback.text.toString())

        }
        bindingDialog.ivClose.setOnClickListener {
            cDialog.dismiss()
        }

    }



    private fun rideCancelledDialog() {
        val cDialog = Dialog(this, R.style.Theme_Tasker_Dialog)
        val bindingDialog: BottomSheetRidecancellationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this),
            R.layout.bottom_sheet_ridecancellation,
            null,
            false
        )
        cDialog.setContentView(bindingDialog.root)

        cDialog.setCancelable(false)
        cDialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        cDialog.show()

        bindingDialog.tvCRN.setText("Your booking with "+  crnNumber +" has been cancelled successfully.")
        cDialog.dismiss()
        this.finish()

        bindingDialog.ivClose.setOnClickListener {
            cDialog.dismiss()
        }
        bindingDialog.btnOk.setOnClickListener {
            cDialog.dismiss()
        }

    }

}