package com.govahan.com.activities.bookingreview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.ChecksumResponse
import com.govahan.com.model.PaymentSuccessMsgResponse
import com.govahan.com.model.bookingloadermodel.BookingLoaderResponseModel
import com.govahan.com.model.bookingreviewget.BookingReviewModel
import com.govahan.com.model.loaderpaymentsuccessmodel.LoaderPaymentSuccessResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingReviewViewModel   @Inject constructor(private val mainRepository: MainRepository) : ViewModel(){

    val bookingReviewResponse = MutableLiveData<BookingReviewModel>()
    val progressBarStatus = MutableLiveData<Boolean>()
    val bookingLoaderResponseModel = MutableLiveData<BookingLoaderResponseModel>()
    val loaderPaymentSuccessResponseModel = MutableLiveData<LoaderPaymentSuccessResponseModel>()
    var ChecksumResponse= MutableLiveData<ChecksumResponse> ()
    var Paymentsuccessmsgresponse= MutableLiveData<PaymentSuccessMsgResponse> ()


    fun searchLoaderDetailApi(token: String,
                          id: String,
                          task: String,
                          pickup_location: String,
                          pickup_lat: String,
                          pickup_long: String,
                          dropup_location: String,
                          dropup_lat: String,
                          dropup_long: String,
                          booking_date: String,
                          available: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.searchLoaderDetailApi(
                token, id, task, pickup_location,
                pickup_lat, pickup_long, dropup_location, dropup_lat,
                dropup_long, booking_date, available
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                bookingReviewResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }
    fun paymentcheckApi(
        token: String,
        transactionid: String
    ): MutableLiveData<PaymentSuccessMsgResponse> {
        if (Paymentsuccessmsgresponse == null) {
            Paymentsuccessmsgresponse = MutableLiveData()
        }
        viewModelScope.launch {
            try {
                val response = mainRepository.paymentcheckapi(token, transactionid
                )

                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    Paymentsuccessmsgresponse.postValue(response.body())
                }
            }catch (e:Exception) {
                progressBarStatus.value = false
                e.printStackTrace()
            }
        }
        return Paymentsuccessmsgresponse
    }

    fun checkupApi(token : String,
        amount: String, mobile: String,
        user_id: String,
    ): MutableLiveData<ChecksumResponse> {
        if (ChecksumResponse == null) {
            ChecksumResponse = MutableLiveData()
        }
        viewModelScope.launch {
            try {
                val response = mainRepository.checksumApi(token, amount,
                    mobile,user_id  )

                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    ChecksumResponse.postValue(response.body())
                }
            }catch (e:Exception) {
                progressBarStatus.value = false
                e.printStackTrace()
            }
        }
        return ChecksumResponse
    }

    fun bookingLoaderApi(token : String,
                            pick_up_location : String,
                            pick_up_lat : String,
                            pick_up_long : String,
                            drop_location : String,
                            drop_lat : String,
                            drop_long : String,
                            vechicle_id : String,
                            fare : String,
                            payment_mode : String,
                            booking_date : String,
                            booking_time : String,
                            driver_id : String,
                         body_type : String,
                         capacity : String,
                         distance : String,
                         vehicle_numbers : String,
                         booking_relation_id : String
    ) {

        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.bookingLoaderApi(token,pick_up_location ,pick_up_lat ,pick_up_long , drop_location, drop_lat,drop_long
                , vechicle_id,fare , payment_mode,booking_date , booking_time,driver_id ,body_type ,capacity ,distance ,vehicle_numbers,booking_relation_id)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                bookingLoaderResponseModel.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }

    fun loaderPaymentSuccessApi(token : String,
                                pick_up_location : String,
                                pick_up_lat : String,
                                pick_up_long : String,
                                drop_location : String,
                                drop_lat : String,
                                drop_long : String,
                                vechicle_id : String,
                                fare : String,total_fare : String,
                                payment_mode : String,
                                booking_date : String,
                                booking_time : String,
                                driver_id : String,
                                dis : String,
                                body_type : String,
                                capacity : String,
                                distance : String,
                                vehicle_numbers : String,
                                transaction_id : String,
                                payment_status : String,
                                currency : String,
                                passengerPaymentSuccessApi : String
    ) {

        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.loaderPaymentSuccessApi(token,pick_up_location ,pick_up_lat ,pick_up_long , drop_location, drop_lat,drop_long
                , vechicle_id,fare ,total_fare, payment_mode,booking_date ,booking_time ,driver_id ,dis  , body_type , capacity , distance , vehicle_numbers
                , transaction_id, payment_status , currency ,passengerPaymentSuccessApi)

            if (response.isSuccessful) {
                progressBarStatus.value = false
                loaderPaymentSuccessResponseModel.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }
    fun loader_payment_wallet(token : String,
                                pick_up_location : String,
                                pick_up_lat : String,
                                pick_up_long : String,
                                drop_location : String,
                                drop_lat : String,
                                drop_long : String,
                                vechicle_id : String,
                                fare : String,total_fare : String,
                                payment_mode : String,
                                booking_date : String,
                                booking_time : String,
                                driver_id : String,
                                dis : String,
                                body_type : String,
                                capacity : String,
                                distance : String,
                                vehicle_numbers : String,
                                payment_status : String,
                                currency : String,
                              booking_relation_id:String
    ) {

        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.loader_payment_wallet(token,pick_up_location ,pick_up_lat ,pick_up_long , drop_location, drop_lat,drop_long
                , vechicle_id,fare ,total_fare, payment_mode,booking_date ,booking_time ,driver_id ,dis  , body_type , capacity , distance , vehicle_numbers
                , payment_status , currency ,booking_relation_id)

            if (response.isSuccessful) {
                progressBarStatus.value = false
                loaderPaymentSuccessResponseModel.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }






}