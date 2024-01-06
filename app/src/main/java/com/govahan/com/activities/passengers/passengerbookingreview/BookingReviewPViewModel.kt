package com.govahan.com.activities.passengers.passengerbookingreview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.bookingpassengelmodel.BookingPassengerResponseModel
import com.govahan.com.model.bookingreviewpassengerget.BookingReviewPassengerModel
import com.govahan.com.model.loaderaddwalletmodel.LoaderAddWalletResponseModel
import com.govahan.com.model.loaderpaymentsuccessmodel.LoaderPaymentSuccessResponseModel
import com.govahan.com.model.passengerpaymentsuccessmodel.PassengerPaymentSuccessResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingReviewPViewModel  @Inject constructor(private val mainRepository: MainRepository) : ViewModel(){

    val bookingReviewPassengerResponse = MutableLiveData<BookingReviewPassengerModel>()
    val bookingPassengerResponseModel = MutableLiveData<BookingPassengerResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()
    val passengerPaymentSuccessResponseModel = MutableLiveData<PassengerPaymentSuccessResponseModel>()
    val loaderPaymentSuccessResponseModel = MutableLiveData<LoaderPaymentSuccessResponseModel>()
    val AddwalletResponse = MutableLiveData<LoaderAddWalletResponseModel>()

    fun purchase_plan_from_walletApi(
        token: String,
        amount:String,transaction_type:String
    ) {
        progressBarStatus.value = true
        viewModelScope.launch {

            val response =
                mainRepository.purchase_plan_from_walletApi(token,amount,transaction_type)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                AddwalletResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }

    fun searchPassengerDetailApi(token: String,
                          pickup_lat: String,
                          pickup_long: String,
                          dropup_lat: String,
                          dropup_long: String,
                          vehicle_type: String,
                          seat: String,
                          tyers: String,
                          booking_date: String,
                          booking_time: String,
                          vehicle_id: String, id: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.searchPassengerDetailApi(
                token,
                pickup_lat, pickup_long, dropup_lat,
                dropup_long, vehicle_type,seat,tyers,booking_date, booking_time, vehicle_id, id
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                bookingReviewPassengerResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }

    fun passengerPaymentSuccessApi(token : String,
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
                                driver_id : String,
                                dis : String,
                                body_type : String,
                                capacity : String,
                                distance : String,
                                vehicle_numbers : String,
                                transaction_id : String,
                                payment_status : String,
                                currency : String,booking_relation_id:String
    ) {

        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.passengerPaymentSuccessApi(token,pick_up_location ,pick_up_lat ,pick_up_long , drop_location, drop_lat,drop_long
                , vechicle_id,fare ,total_fare, payment_mode,booking_date ,driver_id ,dis  , body_type , capacity , distance , vehicle_numbers
                , transaction_id, payment_status , currency ,booking_relation_id )

            if (response.isSuccessful) {
                progressBarStatus.value = false
                passengerPaymentSuccessResponseModel.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }

    fun passenger_payment_wallet(token : String,
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
                                 booking_relation_id : String
    ) {

        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.passenenger_payment_wallet(token,pick_up_location ,pick_up_lat ,pick_up_long , drop_location, drop_lat,drop_long
                , vechicle_id,fare ,total_fare, payment_mode,booking_date ,booking_time,driver_id ,dis  , body_type , capacity , distance , vehicle_numbers
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