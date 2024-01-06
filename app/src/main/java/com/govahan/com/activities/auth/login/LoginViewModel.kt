package com.govahan.com.activities.auth.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.activities.ReferNEarnResponse
import com.govahan.com.data.MainRepository
import com.govahan.com.model.loginOtpModel.LoginOtpResponseModel
import com.govahan.com.model.loginResponse.LoginResponseModel
import com.govahan.com.util.Utils

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(private val mainRepository: MainRepository, @ApplicationContext val context: Context, private val utils : Utils): ViewModel() {
    val userLoginResponse = MutableLiveData<LoginResponseModel>()
    val refernearnResponse = MutableLiveData<ReferNEarnResponse>()
    val progressBarStatus = MutableLiveData<Boolean>()
    val userOtpVerificationResponse = MutableLiveData<LoginOtpResponseModel>()

    fun userLogin(mobile : String,
                  device_id: String,
                  device_type: String,
                  device_name: String,
                  device_token: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            try {
                val response = mainRepository.userLogin(mobile,device_id,device_type,device_name,device_token)
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    userLoginResponse.postValue(response.body())
                }
                else {
                    progressBarStatus.value = false
                    Log.d("TAG", response.body().toString())
                }
            }
            catch (e: Exception) {
                e.printStackTrace()

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
   fun refer_userApi(token: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            try {
                val response = mainRepository.refer_userApi(token)
                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    refernearnResponse.postValue(response.body())
                }
                else {
                    progressBarStatus.value = false
                    Log.d("TAG", response.body().toString())
                }
            }
            catch (e: Exception) {
                e.printStackTrace()

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

    fun userOtpVerification(otp : String , mobile : String,referalcode:String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.verifyOTPForLogIn(otp, mobile,referalcode)
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                userOtpVerificationResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }

    }
}