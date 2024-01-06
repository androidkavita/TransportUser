package com.govahan.com.activities.passengerCancelledBookingDetails

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.passengerOngoinghistorydetailmodel.PassengerOngoingHistoryDetailResponseModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassengerCancelledBookingDetailsViewModel   @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel(){

    val passengerCancelledHistoryDetailResponse = MutableLiveData<PassengerOngoingHistoryDetailResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()


    fun passengerOngoingHistoryDetailApi(token: String,
                                      booking_id: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.passengerOngoingHistoryDetailApi(
                token, booking_id
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                passengerCancelledHistoryDetailResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }



}