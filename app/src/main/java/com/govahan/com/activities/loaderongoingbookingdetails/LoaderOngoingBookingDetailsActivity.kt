package com.govahan.com.activities.loaderongoingbookingdetails

import android.app.Dialog
import android.content.ContentValues.TAG
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
import com.govahan.com.activities.loaderraisecomplaint.LoaderRaiseComplaintActivity
import com.govahan.com.activities.loadertracktruckdriver.LoaderTrackTruckDriverActivity
import com.govahan.com.adapters.LoaderCancelTripReasonAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityBookingDetailsBinding
import com.govahan.com.databinding.BottomSheetCancelTripBinding
import com.govahan.com.databinding.BottomSheetRidecancellationBinding
import com.govahan.com.model.loadercancelreasonmodel.LoaderCancelReasonData
import com.govahan.com.model.ongoingloadertriphistorymodel.OngoingLoaderHistoryData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoaderOngoingBookingDetailsActivity : BaseActivity() {
    private lateinit var binding : ActivityBookingDetailsBinding
    private var selectedLoaderOngoingHistoryData: OngoingLoaderHistoryData? = null
    private val viewModel: LoaderOngoingBookingDetailsViewModel by viewModels()


    private var listData: ArrayList<LoaderCancelReasonData> = ArrayList()
    private var loaderCancelReasonAdapter: LoaderCancelTripReasonAdapter? = null
    var crnNumber = ""
    private var listReasonType_id:ArrayList<String> = ArrayList()
    var reasontypevalue_id: String? = ""
    private lateinit var str: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_details)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Booking Details")

        binding.btnTracktruck.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, LoaderTrackTruckDriverActivity::class.java)
            intent.putExtra("BookingId", selectedLoaderOngoingHistoryData?.bookingId!!.toString())
            startActivity(intent)
        })


        binding.btnRaiseComplaint.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoaderRaiseComplaintActivity::class.java)
            intent.putExtra("BookingId", selectedLoaderOngoingHistoryData?.bookingId!!.toString())
            startActivity(intent)
        })

        val data = intent.extras
        selectedLoaderOngoingHistoryData = data?.getParcelable<OngoingLoaderHistoryData>("loaderOngoingHistoryDetails")

        Log.d("TAG___", "onCreate: " + selectedLoaderOngoingHistoryData!!.bookingId.toString())

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.loaderOngoingHistoryDetailResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")



                    if(it.data==null&&it.data.equals(" ")||it.data.equals(null)){
                        toast("no data")
                    }
                  else{
                    for(i in it.data.indices) {
                        toast(it.message)
                        binding.tvBookingId.text = it.data[i].booking_id
                        binding.tvTripStatus.text = it.data[i].booking_status
                        binding.tvTotalfare.text = it.data[i].fare
                        binding.tvDate.text = it.data[i].booking_date
                        binding.tvPickup.text = it.data[i].picup_location
                        binding.tvDropoff.text = it.data[i].drop_location
                        binding.tvVehicleType.text = it.data[i].vehicle_name
                        binding.tvBodyType.text = it.data[i].body_type
                        binding.tvVehicleNumber.text = it.data[i].vehicle_numbers
                        binding.tvTotalLoads.text = it.data[i].capacity
                        binding.tvPartyName.text = it.data[i].booking_time


                        if (it.data[i].payment_mode.equals("1")) {
                            binding.tvPaymentMethod.setText("Cash")
                        } else if (it.data[i].payment_mode.equals("2")) {
                            binding.tvPaymentMethod.setText("Online")
                        }else{
                            binding.tvPaymentMethod.setText("Wallet")
                        }

                       binding.tvPartyName.text=it.data[i].vehicle_owner_name
                        binding.tvDriverName.text=it.data[i].driver_name




                        binding.tvUseremail.text = it.userDetails.email
                        binding.tvUsername.text = it.userDetails.name
                        binding.tvUserphone.text = it.userDetails.mobile_number



                        /*if(it.data[i].rideCancelStatus.toString().equals("0")){
                            binding.llBtnCancel.visibility = View.VISIBLE
                        }
                        else if(it.rideCancelStatus.toString().equals("1")){
                            binding.llBtnCancel.visibility = View.GONE
                        }*/



                    }

                    }

                Log.d(TAG, "onCreatehis: "+"Bearer " + userPref.user.apiToken+
                    selectedLoaderOngoingHistoryData?.bookingId!!)


            } else {
                toast(it.message!!)
            }
        }

        viewModel.loaderOngoingHistoryDetailApi(
            "Bearer " + userPref.user.apiToken,
            selectedLoaderOngoingHistoryData?.bookingId!!
        )

        viewModel.getLoaderCancelReasonListApi("Bearer " + userPref.user.apiToken)

        binding.btnRaiseComplaint.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, LoaderRaiseComplaintActivity::class.java)
            intent.putExtra("BookingId", selectedLoaderOngoingHistoryData?.bookingId!!.toString())
            startActivity(intent)
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

        viewModel.loaderCancelReasonResponse.observe(this) {
            if (it.status == 1) {
                toast(it.message!!)
                listData.clear()
                listData.addAll(it.data)
                loaderCancelReasonAdapter =
                    LoaderCancelTripReasonAdapter(this, listData)
                bindingDialog.rvReasons.apply {
                    adapter = loaderCancelReasonAdapter
                    layoutManager = LinearLayoutManager(context)

                }
            } else {
                Log.d("Response", it.toString())
                toast(it.message!!)
            }
        }

        viewModel.loaderTripCancelResponse.observe(this) {
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
            listReasonType_id.clear()
            bindingDialog.etFeedback.text.toString()
            for (i in 0 until bindingDialog.rvReasons.childCount){
                val cbReason =bindingDialog.rvReasons.getChildAt(i).findViewById(R.id.cb_reason) as CheckBox
                if (cbReason.isChecked){
                    val id=listData[i].id
                    listReasonType_id.add(id.toString())

                    reasontypevalue_id =  listReasonType_id.toString()
                    str= android.text.TextUtils.join(",", listReasonType_id)
                }
            }


            viewModel.loaderTripCancelApi(
                "Bearer " + userPref.user.apiToken,
                selectedLoaderOngoingHistoryData?.bookingId!!,
                str,
                bindingDialog.etFeedback.text.toString()
            )
            Log.d("CheckBoxInfo",   selectedLoaderOngoingHistoryData?.bookingId!!+str+bindingDialog.etFeedback.text.toString())

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