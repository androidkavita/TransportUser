package com.govahan.com.activities.loadercompletedbookingdetails

import android.app.Dialog
import android.content.Intent
import android.net.Uri
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
import com.govahan.com.activities.invoicesummaryloader.InvoiceSummaryDetailsViewModel
import com.govahan.com.activities.loaderraisecomplaint.LoaderRaiseComplaintActivity
import com.govahan.com.adapters.LoaderCancelTripReasonAdapter
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityLoaderCompletedBookingDetailsBinding
import com.govahan.com.databinding.BottomSheetCancelTripBinding
import com.govahan.com.databinding.BottomSheetRidecancellationBinding
import com.govahan.com.model.completedloadertriphistorymodel.CompletedLoaderHistoryData
import com.govahan.com.model.loadercancelreasonmodel.LoaderCancelReasonData
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoaderCompletedBookingDetailsActivity : BaseActivity() {

    private lateinit var binding : ActivityLoaderCompletedBookingDetailsBinding
    private var selectedLoaderCompletedHistoryData: CompletedLoaderHistoryData? = null
    private val viewModel: LoaderCompletedBookingDetailsViewModel by viewModels()
    private val viewModel1: InvoiceSummaryDetailsViewModel by viewModels()
    private var listData: ArrayList<LoaderCancelReasonData> = ArrayList()
    private var loaderCancelReasonAdapter: LoaderCancelTripReasonAdapter? = null
    var crnNumber = ""
    private var listReasonType_id:ArrayList<String> = ArrayList()
    var reasontypevalue_id: String? = ""
    private lateinit var str: String
    var downlloadpdf= ""
    var downloadbilty= ""
    var downloadsignature= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_loader_completed_booking_details)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_loader_completed_booking_details)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Booking Details")

//        binding.btnDownload.setOnClickListener{
//            viewModel1.loader_invoice_url_second("Bearer " + userPref.user.apiToken,
//                selectedLoaderCompletedHistoryData?.bookingId!!!!
//            )
//        }
        binding.podDownload.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(downlloadpdf))
            startActivity(browserIntent)
            toast("Pod Downloaded successfully!")
        }
        binding.bilty.setOnClickListener {
            if (downloadbilty.isNullOrEmpty()){
                toast("No Road Challan/Fine Found")
            }else{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(downloadbilty))
                startActivity(browserIntent)
                toast("Road Challan Downloaded successfully!")
            }
        }
        viewModel1.loaderSendMailResponseModel.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast("Email Sent L successfully!")
            } else {
                toast(it.message!!)
            }
        }
        binding.btnEmail.setOnClickListener(View.OnClickListener {
            viewModel1.sendMailLoaderInvoiceApi("Bearer " + userPref.user.apiToken, selectedLoaderCompletedHistoryData?.bookingId!!.toString())
        })
        viewModel1.loaderDownloadInvoiceResponseModel.observe(this) {
            if (it.status == 1) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
                toast("Invoice Downloaded successfully!")
            } else {
                toast(it.message!!)
            }
        }

        binding.signature.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(downloadsignature))
            startActivity(browserIntent)
            toast("Signature Downloaded successfully!")
        }

        val data = intent.extras
        selectedLoaderCompletedHistoryData = data?.getParcelable<CompletedLoaderHistoryData>("loaderCompletedHistoryDetails")

       Log.d("TAG___", "onCreate: " + selectedLoaderCompletedHistoryData!!.bookingId.toString())

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.loaderCompletedHistoryDetailResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message)
                binding.tvBookingId.text = it.data[0].booking_id
                binding.tvTripStatus.text = it.data[0].booking_status
                binding.tvPaid.text = "₹${it.data[0].fare}"
                binding.tvBookingId.text = it.data[0].booking_id
                binding.tvDate.text = it.data[0].booking_date
                binding.tvTime.text = it.data[0].booking_time
                binding.tvPickup.text = it.data[0].picup_location
                binding.tvDropoff.text = it.data[0].drop_location
                binding.tvVehicleType.text = it.data[0].vehicle_name
                binding.tvBodyType.text = it.data[0].bodyname
                binding.tvVehicleNumber.text = it.data[0].vehicle_number
                binding.tvTotalLoads.text = it.data[0].capacity
                binding.tvPartyName.text = it.data[0].vehicle_owner_name
                binding.tvDriverName.text = it.data[0].driver_name

                if (it.data[0].payment_mode.equals("1")) {
                    binding.tvPaymentMethod.setText("Cash")
                } else if (it.data[0].payment_mode.equals("2")) {
                    binding.tvPaymentMethod.setText("Online")
                }else {
                    binding.tvPaymentMethod.setText("Wallet")
                }
                binding.tvUseremail.text = it.userDetails.email
                binding.tvUsername.text = it.userDetails.name
                binding.tvUserphone.text = it.userDetails.mobile_number
            } else {
                toast(it.message!!)
            }
        }

        viewModel.loaderOngoingHistoryDetailApi(
            "Bearer " + userPref.user.apiToken,
            selectedLoaderCompletedHistoryData?.bookingId!!
        )
         viewModel.UploadDocumentsResponseApi(
            "Bearer " + userPref.user.apiToken,
            selectedLoaderCompletedHistoryData?.bookingId!!
        )

        viewModel.UploadDocuments.observe(this) {
            if (it.status == 1) {
                if (it.data.pod==null){
                    downlloadpdf=""
                }
                else{
                    downlloadpdf = it.data.pod.toString()
                }
                if (it.data.builty==null){
                    downloadbilty=""
                }
                else{
                    downloadbilty = it.data.builty.toString()

                }
                if (it.data.signature==null){
                    downloadsignature=""
                }
                else{
                    downloadsignature = it.data.signature.toString()
                }

            } else {
                toast(it.message!!)
            }
        }

        viewModel.getLoaderCancelReasonListApi("Bearer " + userPref.user.apiToken)

        binding.btnRaiseComplaint.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, LoaderRaiseComplaintActivity::class.java)
            intent.putExtra("BookingId", selectedLoaderCompletedHistoryData?.bookingId!!.toString())
            startActivity(intent)
        })

        viewModel1.loaderDownloadInvoiceResponseModel.observe(this) {
            if (it.status == 1) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
                toast("Invoice Downloaded successfully!")
            } else {
                toast(it.message!!)
            }
        }
        binding.finalInvoice.setOnClickListener {
            viewModel1.loaderDownloadInvoiceUrlApi("Bearer " + userPref.user.apiToken,
                selectedLoaderCompletedHistoryData?.bookingId!!.toString()
            )
        }
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

            viewModel.loaderTripCancelApi(
                "Bearer " + userPref.user.apiToken,
                selectedLoaderCompletedHistoryData?.bookingId!!.toString(),
                str,
                bindingDialog.etFeedback.text.toString()
            )
            Log.d("CheckBoxInfo",   selectedLoaderCompletedHistoryData?.bookingId!!.toString()+str+bindingDialog.etFeedback.text.toString())

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