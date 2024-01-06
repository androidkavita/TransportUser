package com.govahan.com.activities.invoicesummaryloader

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.loaderAddRaiseComplaintModel.LoaderAddRaiseComplaintResponseModel
import com.govahan.com.model.loaderInvoiceDetailModel.LoaderInvoiceDetailResponseModel
import com.govahan.com.model.loaderInvoiceDownloadModel.LoaderDownloadInvoiceUrlResponseModel
import com.govahan.com.model.sendmailmodel.LoaderSendMailResponseModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class InvoiceSummaryDetailsViewModel   @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel(){

    val loaderInvoiceSummaryDetailResponse = MutableLiveData<LoaderInvoiceDetailResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()
    val loaderSendMailResponseModel = MutableLiveData<LoaderSendMailResponseModel>()
    val loaderDownloadInvoiceResponseModel = MutableLiveData<LoaderDownloadInvoiceUrlResponseModel>()

    fun loaderInvoiceDetailApi(token: String,
                               invoice_numbers: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.loaderInvoiceDetailApi(
                token, invoice_numbers
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                loaderInvoiceSummaryDetailResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }

    fun sendMailLoaderInvoiceApi(token: String,
                                        booking_id: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.sendMailLoaderInvoiceApi(
                    token, booking_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    loaderSendMailResponseModel.postValue(response.body())
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

    fun loaderDownloadInvoiceUrlApi(token: String,
                                       booking_id: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.loaderDownloadInvoiceUrlApi(
                    token, booking_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    loaderDownloadInvoiceResponseModel.postValue(response.body())
                } else {
                    progressBarStatus.value = false
                    Log.d("TAG", response.body().toString())
                }
            }
        } catch (e: Exception) {
            progressBarStatus.value = false
            e.printStackTrace()
            if (e is ConnectException) {
                utils.simpleAlert(
                    context, "Error", "Please check your Internet connection"
                )
            } else {
                utils.simpleAlert(
                    context,
                    "Some Error Occurred",
                    "Please check your Internet connection"
                )
            }

        }
     }
        fun loader_invoice_url_second(token: String,
                                       booking_id: String) {
            progressBarStatus.value = true
            try {
                viewModelScope.launch {
                    val response = mainRepository.loader_invoice_url_second(
                        token, booking_id
                    )
                    if (response.isSuccessful) {
                        progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                        loaderDownloadInvoiceResponseModel.postValue(response.body())
                    } else {
                        progressBarStatus.value = false
                        Log.d("TAG", response.body().toString())
                    }
                }
            } catch (e: Exception) {
                progressBarStatus.value = false
                e.printStackTrace()
                if (e is ConnectException) {
                    utils.simpleAlert(
                        context, "Error", "Please check your Internet connection"
                    )
                } else {
                    utils.simpleAlert(
                        context,
                        "Some Error Occurred",
                        "Please check your Internet connection"
                    )
                }

            }
        }
            fun passenenger_invoice_url_second(token: String,
                                       booking_id: String) {
            progressBarStatus.value = true
            try {
            viewModelScope.launch {
                val response = mainRepository.passenenger_invoice_url_second(
                    token, booking_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    loaderDownloadInvoiceResponseModel.postValue(response.body())
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