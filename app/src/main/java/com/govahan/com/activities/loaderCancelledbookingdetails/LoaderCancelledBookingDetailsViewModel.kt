package com.govahan.com.activities.loaderCancelledbookingdetails

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.loaderongoinghistorydetailmodel.LoaderOngoingHistoryDetailResponseModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoaderCancelledBookingDetailsViewModel   @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel(){

    val loaderCancelledHistoryDetailResponse = MutableLiveData<LoaderOngoingHistoryDetailResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()



    fun loaderOngoingHistoryDetailApi(token: String,
                                      booking_id: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.loaderOngoingHistoryDetailApi(
                token, booking_id
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                loaderCancelledHistoryDetailResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }






}