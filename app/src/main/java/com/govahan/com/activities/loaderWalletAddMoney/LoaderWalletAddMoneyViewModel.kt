package com.govahan.com.activities.loaderWalletAddMoney

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.govahan.com.data.MainRepository
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoaderWalletAddMoneyViewModel  @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel() {

    //var addMoneyWalletResponse = MutableLiveData<LoaderAddWalletResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()





   /* fun loaderWalletAddMoneyApi(token: String,
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
    }*/





}