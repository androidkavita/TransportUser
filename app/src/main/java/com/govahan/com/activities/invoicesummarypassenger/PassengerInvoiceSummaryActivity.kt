package com.govahan.com.activities.invoicesummarypassenger

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.activities.invoicesummaryloader.InvoiceSummaryDetailsViewModel
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityPassengerInvoiceSummaryBinding
import com.govahan.com.model.passengerinvoicelistmodel.PassengerInvoiceData
import com.govahan.com.util.DateFormat
import com.govahan.com.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class PassengerInvoiceSummaryActivity : BaseActivity() {

    private lateinit var binding: ActivityPassengerInvoiceSummaryBinding
    private var selectedInvoiceListData: String? = null
    private var selectedPassengerListData: PassengerInvoiceData? = null
    private var selectedPassengerInvoiceBookingId: String? = null
    private val viewModel: PassengerInvoiceSummaryDetailsViewModel by viewModels()
    private val viewModel1: InvoiceSummaryDetailsViewModel by viewModels()
    var url :String =""
    var downlloadpdf :String = ""
    var newurl :String = ""
    var freightamount= ""
    var tolltax= ""
    var totalamount=""
    var manager: DownloadManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_passenger_invoice_summary)

        binding.header.ivBack.setOnClickListener(View.OnClickListener {
            finish()
        })
        emailresponse()
        binding.header.tvHeaderText.setText("Invoice Summary")

        val data = intent.extras

        selectedInvoiceListData = intent.getStringExtra("loaderInvoiceDetails").toString()
        selectedPassengerInvoiceBookingId = intent.getStringExtra("passengerInvoiceBookingId").toString()

        viewModel.progressBarStatus.observe(this) {
            if (it) {
                showProgressDialog()
            } else {
                hideProgressDialog()
            }
        }
        viewModel.passengerDownloadInvoiceResponseModel.observe(this) {
            if (it.status == 1) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.url)))
                toast("Invoice Downloaded successfully!")
            } else {
                toast(it.message!!)
            }
        }

        viewModel.passengerInvoiceSummaryDetailResponse.observe(this) {
            if (it.status == 1) {
                // toast("booking Successful")
                toast(it.message!!)

                binding.tvInvoiceNumber.text = it.data[0].invoice_number
                binding.tvInvoiceDate.text = DateFormat.TimeFormat(it.data[0].created_at)

                binding.tvUsername.text = it.userDetails?.name
                binding.tvUserphone.text = it.userDetails?.mobile_number
                binding.tvUseremail.text = it.userDetails?.email

                binding.tvVehicleType.text = it.data[0].vehicle_name
                binding.tvTotalLoads.text = it.data[0].capacity
                binding.tvBodyType.text = it.data[0].body_type
                binding.tvVehicleNumber.text = it.data[0].vehicle_numbers

                binding.tvDriverName.text = it.data[0].driver_name.driver_name
                binding.tvDriverNumber.text = it.data[0].driver_name.mobile_number

                binding.tvDepartureplace.text = it.data[0].picup_location
                binding.tvArrivalplace.text = it.data[0].drop_location
                binding.tvBookingDate.text = DateFormat.TimeFormat(it.data[0].booking_date)
//                binding.tvCompleteDate.text = it.data[0].bookingDate
                binding.tvCharges.text = "₹${it.data[0].fare}"

                binding.tvFuelcharges.text="₹${it.data[0].fuel_charge}"
                binding.driverCharges.text="₹${it.data[0].driver_fee}"
                binding.tvCharges.text="₹${it.data[0].toll_tax}"
                freightamount= it.data[0].freight_amount
                tolltax=it.data[0].tax
                binding.fare.text="₹${it.data[0].fare}"
                binding.tax.text="₹${addNumberValueTwoDegit(it.data[0].tax)}"
                totalamount= (freightamount.toInt() + tolltax.toFloat()).toString()
                binding.tvTotalamount.text="₹${totalamount}"
                if(it.data[0].payment_mode.equals("1"))
                {binding.tvPaymentMode.setText("Cash")}
                else  if(it.data[0].payment_mode.equals("2"))
                {binding.tvPaymentMode.setText("Online")}
                  else  if(it.data[0].payment_mode.equals("3"))
                {
                      binding.tvPaymentMode.setText("Wallet")}
            } else {
                toast(it.message!!)
            }
        }


        viewModel.passengerInvoiceDetailApi(
            "Bearer " + userPref.user.apiToken,
            selectedInvoiceListData.toString()
        )


        binding.btnEmail.setOnClickListener {
            viewModel.sendMailPassengerInvoiceApi("Bearer " + userPref.user.apiToken, selectedPassengerInvoiceBookingId.toString())
        }

        binding.btnDownload.setOnClickListener{
            viewModel1.passenenger_invoice_url_second("Bearer " + userPref.user.apiToken, selectedPassengerInvoiceBookingId.toString())
        }


    }
    fun emailresponse(){
        viewModel.passengerSendMailResponseModel.observe(this) {
            if (it.status == 1) {
                toast("Email Sent P successfully!")

            } else {
                toast(it.message!!)
            }
        }

    }
    fun addNumberValueTwoDegit(totalAmount: String?): String {
        val amount: Double? = totalAmount?.toDouble()
        val formatter = DecimalFormat("####.00")
        val formatted: String = formatter.format(amount)
        return formatted
    }

}