package com.govahan.com.activities.passengers.passengertripdetails

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.passengercancelreasonmodel.PassengerCancelReasonListResponseModel
import com.govahan.com.model.passengercanceltripmodel.PassengerTripCancelResponseModel
import com.govahan.com.model.passengertripmanagementmodel.PassengerTripManagementDetailResponse
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class PassengerTripDetailsViewModel   @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel(){

    val passengerTripDetailResponse = MutableLiveData<PassengerTripManagementDetailResponse>()
    val passengerCancelReasonResponse = MutableLiveData<PassengerCancelReasonListResponseModel>()
    val passengerTripCancelResponse = MutableLiveData<PassengerTripCancelResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()




    fun passengerTripManagementDetailApi(token: String,
                                      booking_id: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.passengerTripManagementDetailApi(
                token, booking_id
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                passengerTripDetailResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }


    fun getPassengerCancelReasonListApi(token: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.getPassengerCancelReasonListApi(
                    token
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    passengerCancelReasonResponse.postValue(response.body())
                } else {
                    progressBarStatus.value = false
                    Log.d("TAG", response.body().toString())
                }

            }

        }
        catch (e: Exception) {
            progressBarStatus.value = false
            e.printStackTrace()
            if (e is ConnectException) {
                utils.simpleAlert(
                    context, "Error", "Please check your Internet connection")
            } else {
                utils.simpleAlert(context, "Some Error Occurred", "Please check your Internet connection")
            }

        }
    }

    fun passengerTripCancelApi(token: String,
                            booking_id: String,
                            reasons_id: String,
                            message: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.passengerTripCancelApi(
                    token,
                    booking_id,
                    reasons_id,
                    message
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    passengerTripCancelResponse.postValue(response.body())
                } else {
                    progressBarStatus.value = false
                    Log.d("TAG", response.body().toString())
                }
            }
        }
        catch (e: Exception) {
            progressBarStatus.value = false
            e.printStackTrace()
            if (e is ConnectException) {
                utils.simpleAlert(
                    context, "Error", "Please check your Internet connection")
            } else {
                utils.simpleAlert(context, "Some Error Occurred", "Please check your Internet connection")
            }

        }
    }





}