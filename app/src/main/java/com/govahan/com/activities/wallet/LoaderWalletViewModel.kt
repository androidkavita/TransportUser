package com.govahan.com.activities.wallet

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.PaymentSuccessMsgResponse
import com.govahan.com.model.loaderaddwalletmodel.LoaderAddWalletResponseModel
import com.govahan.com.model.loaderwalletfiltermodel.LoaderWalletFilterResponseModel
import com.govahan.com.model.loaderwalletlistmodel.LoaderWalletListResponseModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class LoaderWalletViewModel  @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel() {

    var addMoneyWalletResponse = MutableLiveData<LoaderAddWalletResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()
    val getLoaderWalletListResponse = MutableLiveData<LoaderWalletListResponseModel>()
    val downloadloaderlist = MutableLiveData<LoaderWalletListResponseModel>()
    val loaderWalletFilterListResponse = MutableLiveData<LoaderWalletFilterResponseModel>()
    val addmoneytowalletresponse = MutableLiveData<LoaderAddWalletResponseModel>()
    var Paymentsuccessmsgresponse= MutableLiveData<PaymentSuccessMsgResponse> ()



    fun loaderWalletAddMoneyApi(token: String,
                                amount: String) {
        progressBarStatus.value = true
        try {
        viewModelScope.launch {
            val response = mainRepository.loaderWalletAddMoneyApi(
                token,
                amount
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                addMoneyWalletResponse.postValue(response.body())
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
    fun paymentcheckApi(token: String,
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


    fun loaderWalletDownload(token: String) {
        progressBarStatus.value = true
        try {
        viewModelScope.launch {
            val response = mainRepository.my_wallet_list_download(
                token
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                downloadloaderlist.postValue(response.body())
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

 fun my_wallet_payment(
        token: String,
        type: String,
        transaction_id: String,
        amount: String,
    ) {
        progressBarStatus.value = true
        viewModelScope.launch {

            val response =
                mainRepository.my_wallet_payment(token,type,transaction_id,amount)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                addmoneytowalletresponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }

    fun loaderWalletListApi(token: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.loaderWalletListApi(
                    token
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    getLoaderWalletListResponse.postValue(response.body())
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




    fun loaderWalletFilterApi(token: String,
                              amount: String,transaction_type:String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.loaderWalletFilterApi(
                    token, amount,transaction_type
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    loaderWalletFilterListResponse.postValue(response.body())
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