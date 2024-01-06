package com.govahan.com.activities.invoicesummarypassenger

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.passengerInvoiceDetailModel.PassengerInvoiceDetailResponseModel
import com.govahan.com.model.passengerdownloadinvoicemodel.PassengerDownloadInvoiceUrlResponseModel
import com.govahan.com.model.sendmailmodel.LoaderSendMailResponseModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class PassengerInvoiceSummaryDetailsViewModel   @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel(){

    val passengerInvoiceSummaryDetailResponse = MutableLiveData<PassengerInvoiceDetailResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()
    val passengerSendMailResponseModel = MutableLiveData<LoaderSendMailResponseModel>()
    val passengerDownloadInvoiceResponseModel = MutableLiveData<PassengerDownloadInvoiceUrlResponseModel>()



    fun passengerInvoiceDetailApi(token: String,
                               invoice_numbers: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.passengerInvoiceDetailApi(
                token, invoice_numbers
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                passengerInvoiceSummaryDetailResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }



    fun passengerDownloadInvoiceUrlApi(token: String, booking_id: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.passengerDownloadInvoiceUrlApi(
                    token, booking_id)
                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    passengerDownloadInvoiceResponseModel.postValue(response.body())
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


    fun sendMailPassengerInvoiceApi(token: String,
                                 booking_id: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.sendMailPassengerInvoiceApi(
                    token, booking_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    passengerSendMailResponseModel.postValue(response.body())
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







//
//    fun passengerDownloadInvoiceUrlApi(token :String, booking_id: String){
//        //        if (BusinessListResponse == null) {
//        //            BusinessListResponse = MutableLiveData()
//        //        }
//        viewModelScope.launch {
//            try {
//                val response = mainRepository.passengerDownloadInvoiceUrlApi(token,booking_id)
//
//                if (response.isSuccessful) {
//                    progressBarStatus.value = false
//                    passengerDownloadInvoiceResponseModel.postValue(response.body())
//                }
//            }catch (e:Exception) {
//                progressBarStatus.value = false
//                e.printStackTrace()
//            }
//        }
//        //        return BusinessListResponse
//    }














}