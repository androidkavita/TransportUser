package com.govahan.com.activities.tripdetails

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.loadercancelreasonmodel.LoaderCancelReasonListResponseModel
import com.govahan.com.model.loadercanceltripmodel.LoaderTripCancelResponseModel
import com.govahan.com.model.loaderrescheduletripmodel.LoaderRescheduleTripResponseModel
import com.govahan.com.model.loadertripmanagementmodel.LoaderTripManagementDetailResponse
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class TripDetailsViewModel   @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel(){

    val loaderTripDetailResponse = MutableLiveData<LoaderTripManagementDetailResponse>()
    val loaderCancelReasonResponse = MutableLiveData<LoaderCancelReasonListResponseModel>()
    val loaderTripCancelResponse = MutableLiveData<LoaderTripCancelResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()
    val loaderRescheduleTripResponse = MutableLiveData<LoaderRescheduleTripResponseModel>()



    fun loaderTripManagementDetailApi(token: String,
                                      booking_id: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.loaderTripManagementDetailApi(
                token, booking_id
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                loaderTripDetailResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }


    fun getLoaderCancelReasonListApi(token: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.getLoaderCancelReasonListApi(
                    token
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    loaderCancelReasonResponse.postValue(response.body())
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

    fun loaderTripCancelApi(token: String,
                            booking_id: String,
                            reasons_id: String,
                            message: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.loaderTripCancelApi(
                    token,
                    booking_id,
                    reasons_id,
                    message
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    loaderTripCancelResponse.postValue(response.body())
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






    fun loaderRescheduleTripApi(token: String,
                            booking_id: String,
                            reasons_id: String,
                            message: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.loaderRescheduleTripApi(
                    token,
                    booking_id,
                    reasons_id,
                    message
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    loaderRescheduleTripResponse.postValue(response.body())
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