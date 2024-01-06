package com.govahan.com.activities.transportowner

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.ReviewsModelClass
import com.govahan.com.model.transportownerget.TransportOwnerModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class TransportOwnerViewModel    @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel() {

    val transportOwnerResponse = MutableLiveData<TransportOwnerModel>()
    var getRatingResponse = MutableLiveData<ReviewsModelClass>()

    val progressBarStatus = MutableLiveData<Boolean>()


    fun owner_driverDetailApi(token: String,
                              driver_id: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.owner_driverDetailApi(
                token, driver_id
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                transportOwnerResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }










    fun getRatingApi(token: String,
                               driver_id: String,
                               ) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.getRatingApi(
                    token,
                    driver_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    getRatingResponse.postValue(response.body())
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



}