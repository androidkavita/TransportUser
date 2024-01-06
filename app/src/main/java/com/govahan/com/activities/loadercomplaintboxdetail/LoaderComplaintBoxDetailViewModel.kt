package com.govahan.com.activities.loadercomplaintboxdetail

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.ReviewsModelClass
import com.govahan.com.model.loaderAddRaiseComplaintModel.LoaderAddRaiseComplaintResponseModel
import com.govahan.com.model.loaderComplaintListDetailModel.LoaderComplaintListDetailResponseModel

import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class LoaderComplaintBoxDetailViewModel  @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel() {
    val resolvedResponse = MutableLiveData<LoaderAddRaiseComplaintResponseModel>()

    val progressBarStatus = MutableLiveData<Boolean>()
    val loaderComplaintListDetailResponse = MutableLiveData<LoaderComplaintListDetailResponseModel>()
    val reviewsresonse = MutableLiveData<ReviewsModelClass>()



    fun complaint_resolved(token: String,
                           id: String,type:String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.complaint_resolved(
                token, id,type
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                resolvedResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }
    fun loaderComplaintListDetailApi(token: String,
                                     booking_id: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.loaderComplaintListDetailApi(
                    token, booking_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    loaderComplaintListDetailResponse.postValue(response.body())
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

fun search_loader_driver_review(token: String,
                                     driver_id: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.search_loader_driver_review(
                    token, driver_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    reviewsresonse.postValue(response.body())
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