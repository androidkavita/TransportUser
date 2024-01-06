package com.govahan.com.activities.invoicesummaryloader

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityInvoiceSummaryBinding
import com.govahan.com.util.DateFormat
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class LoaderInvoiceSummaryActivity : BaseActivity() {
    private lateinit var binding: ActivityInvoiceSummaryBinding
    private var selectedInvoiceListData: String? = null
    private var selectedInvoiceLoaderBookingId: String? = null
    private val viewModel: InvoiceSummaryDetailsViewModel by viewModels()
    var downlloadpdf= ""
    var downloadbilty= ""
    var downloadsignature= ""
    var freightamount= ""
    var tolltax= ""
    var totalamount=""
    var booking_id=""
    var manager: DownloadManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_invoice_summary)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        binding.header.tvHeaderText.setText("Invoice Summary")
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
        binding.signature.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(downloadsignature))
            startActivity(browserIntent)
            toast("Signature Downloaded successfully!")
        }

        val data = intent.extras

        selectedInvoiceListData = intent.getStringExtra("loaderInvoiceDetails").toString()
        selectedInvoiceLoaderBookingId = intent.getStringExtra("loaderInvoiceBookingId").toString()
    //    selectedInvoiceListData = data?.getParcelable<LoaderInvoiceData>("loaderInvoiceDetails")
   //     selectedPassengerListData = data?.getParcelable<PassengerInvoiceData>("loaderInvoiceDetails")

        //Log.d("TAG___", "onCreate: " + selectedInvoiceListData!!.invoiceNumber.toString())

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }

        viewModel.loaderInvoiceSummaryDetailResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)

                try {
                    binding.tvInvoiceNumber.text = it.data[0].invoiceNumber
                    binding.tvInvoiceDate.text = DateFormat.TimeFormat(it.data[0].createdAt)
                    binding.tvUsername.text = it.userDetails?.name
                    booking_id= it.data[0].bookingId.toString()
                    binding.tvUserphone.text = it.userDetails?.mobileNumber
                    binding.tvUseremail.text = it.userDetails?.email
                    binding.tvBodyType.text = it.data[0].bodyType
                    binding.tvVehicleType.text = it.data[0].vehicleName
                    binding.tvVehicleNumber.text = it.data[0].vehicleNumbers
                    binding.tvTotalLoads.text = it.data[0].capacity
                    binding.tvDriverName.text = it.data[0].driver_name?.driver_name
                    binding.tvDriverNumber.text = it.data[0].driver_name?.mobile_number
                    binding.tvDepartureplace.text = it.data[0].picupLocation
                    binding.tvArrivalplace.text = it.data[0].dropLocation
                    binding.tvBookingDate.text = DateFormat.TimeFormat(it.data[0].bookingDate)
                    binding.tvCgst.text = it.data[0].bookingTime
                    binding.tvFuelcharges.text="₹${it.data[0].fuel_charge}"
                    binding.driverCharges.text="₹${it.data[0].driver_fee}"
                    binding.fare.text="₹${it.data[0].fare}"
                    binding.tax.text="₹${addNumberValueTwoDegit(it.data[0].tax)}"
                    binding.tollCharges.text="₹${it.data[0].toll_tax}"
                    freightamount= it.data[0].freight_amount.toString()
                        tolltax=it.data[0].tax.toString()
                    totalamount= (freightamount.toInt() + tolltax.toFloat()).toString()
                    binding.tvTotalamount.text="₹${totalamount}"
                    if (it.data[0].paymentMode.equals("1")) {
                        binding.tvPaymentMode.setText("Cash")
                    } else if (it.data[0].paymentMode.equals("2")) {
                        binding.tvPaymentMode.setText("Online")
                    }else if (it.data[0].paymentMode.equals("3")) {
                        binding.tvPaymentMode.setText("Wallet")
                    }

                    downlloadpdf = it.data[0].pod.toString()
                    if (it.data[0].builty.toString().isNullOrEmpty()){
                        downloadbilty=""
                    }
                    else{
                        downloadbilty = it.data[0].builty.toString()

                    }
                    downloadsignature = it.data[0].signature.toString()

                }catch (e:Exception){
                    e.printStackTrace()
                }
            } else {
                toast(it.message!!)
            }
        }

        viewModel.loaderDownloadInvoiceResponseModel.observe(this) {
            if (it.status == 1) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
                toast("Invoice Downloaded successfully!")
            } else {
                toast(it.message!!)
            }
        }

        viewModel.loaderInvoiceDetailApi(
            "Bearer " + userPref.user.apiToken,
            selectedInvoiceListData.toString()
        )

        binding.btnDownload.setOnClickListener {
            viewModel.loader_invoice_url_second(
                "Bearer " + userPref.user.apiToken,
                booking_id
            )
        }

//        viewModel.loaderSendMailResponseModel.observe(this) {
//            if (it.status == 1) {
//                // toast("booking Successful")
//                toast("Email Sent L successfully!")
//
//            } else {
//                toast(it.message!!)
//            }
//        }
//        binding.btnEmail.setOnClickListener(View.OnClickListener {
//            viewModel.sendMailLoaderInvoiceApi("Bearer " + userPref.user.apiToken, selectedInvoiceLoaderBookingId.toString())
//        })
//
//
//        viewModel.loaderDownloadInvoiceResponseModel.observe(this) {
//            if (it.status == 1) {
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
//                toast("Invoice Downloaded successfully!")
//            } else {
//                toast(it.message!!)
//            }
//        }
//        binding.btnDownload.setOnClickListener(View.OnClickListener {
//            viewModel.loader_invoice_url_second("Bearer " + userPref.user.apiToken,
//                selectedInvoiceLoaderBookingId!!
//            )
//        })

    }
    fun addNumberValueTwoDegit(totalAmount: String?): String {
        val amount: Double? = totalAmount?.toDouble()
        val formatter = DecimalFormat("####.00")
        val formatted: String = formatter.format(amount)
        return formatted
    }

}

