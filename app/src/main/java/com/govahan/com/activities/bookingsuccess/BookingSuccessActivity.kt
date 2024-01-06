package com.govahan.com.activities.bookingsuccess

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.govahan.com.R
import com.govahan.com.activities.bookingconfirmationstatus.BookingConfirmationAndStatusActivity
import com.govahan.com.baseClasses.BaseActivity
import com.govahan.com.databinding.ActivityBookingSuccessBinding
import com.govahan.com.model.bookingloadermodel.BookingLoaderData
import com.govahan.com.model.bookingloadermodel.BookingLoaderDriver
import com.govahan.com.model.bookingloadermodel.BookingLoaderUser
import com.govahan.com.model.loaderpaymentsuccessmodel.LoaderPaymentSuccessData
import com.govahan.com.model.loaderpaymentsuccessmodel.LoaderPaymentSuccessDriver
import com.govahan.com.model.loaderpaymentsuccessmodel.LoaderPaymentSuccessUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingSuccessActivity : BaseActivity() {
    private lateinit var binding : ActivityBookingSuccessBinding
    var modelDataList: BookingLoaderData?=null
    var modelUserList: BookingLoaderUser?=null
    var modelDriverList: BookingLoaderDriver?=null
    var modelRideCode = ""
    var modelOnlineDataList: LoaderPaymentSuccessData?=null
    var modelOnlineUserList: LoaderPaymentSuccessUser?=null
    var modelOnlineDriverList: LoaderPaymentSuccessDriver?=null
    var modelOnlineRideCode = ""
    var paymentMode:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_success)

        if (intent!=null){
//            if(intent.getStringExtra("cash").equals("CASH")) {
//                modelOnlineDataList = (intent.getStringExtra("modelDataList") as LoaderPaymentSuccessData?)
//                modelOnlineUserList = (intent.getStringExtra("modelUserList") as LoaderPaymentSuccessUser?)
//                modelOnlineDriverList =
//                    (intent.getStringExtra("modelDriverList") as LoaderPaymentSuccessDriver?)
//                modelOnlineRideCode=intent.getStringExtra("modelRideCode").toString()
//                Log.d(TAG, "onCreatepay:"+"Cash")
//                paymentMode="cash"
//           }else
               if(intent.getStringExtra("online").equals("ONLINE")) {
                modelDataList = (intent.getStringExtra("modelDataList") as BookingLoaderData?)
                modelUserList = (intent.getStringExtra("modelUserList") as BookingLoaderUser?)
                modelDriverList = (intent.getStringExtra("modelDriverList") as BookingLoaderDriver?)
                modelRideCode = (intent.getStringExtra("modelRideCode1") as String)
                Log.d(TAG, "onCreatepay:"+"Online")
                paymentMode="online"
            }
        }
//        val extras = intent.extras
//        if (extras != null) {
//
//            if() {
//                modelOnlineDataList = (extras.getSerializable("modelDataList") as LoaderPaymentSuccessData?)
//                modelOnlineUserList = (extras.getSerializable("modelUserList") as LoaderPaymentSuccessUser?)
//                modelOnlineDriverList =
//                    (extras.getSerializable("modelDriverList") as LoaderPaymentSuccessDriver?)
//                modelOnlineRideCode="1234"
//                Log.d(TAG, "onCreatepay:"+"Online")
//           }else if(!BookingReviewActivity.bPaymentMode) {
//
//                modelDataList = (extras.getSerializable("modelDataList") as BookingLoaderData?)
//                modelUserList = (extras.getSerializable("modelUserList") as BookingLoaderUser?)
//                modelDriverList =
//                    (extras.getSerializable("modelDriverList") as BookingLoaderDriver?)
//                modelRideCode = (extras.getSerializable("modelRideCode") as String)
//
//                Log.d(TAG, "onCreatepay:"+"Cash")
//            }
//
//
//
//
//
//        }

        binding.btnNext.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, BookingConfirmationAndStatusActivity :: class.java)
                .putExtra("modelDataList", modelDataList)
                .putExtra("modelUserList", modelUserList)
                .putExtra("modelDriverList", modelDriverList)
                .putExtra("modelRideCode", modelRideCode)
                .putExtra("payment_mode","online"))
            finish()
        })
    }
}