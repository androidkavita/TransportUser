package com.govahan.com.activities.settings

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.settingsmsemailmodel.SettingSmsEmailResponseModel
import com.govahan.com.model.settingwhatsappmodel.SettingWhatsappResponseModel

import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class SettingsActivityViewModel  @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel() {

    var whatsappUpdatesResponse = MutableLiveData<SettingWhatsappResponseModel>()
    var smsEmailUpdatesResponse = MutableLiveData<SettingSmsEmailResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()



    fun settingsWhatsappUpdatesApi(token: String,
                                   booking_id: String
    ) {
        progressBarStatus.value = true
        try {
        viewModelScope.launch {
            val response = mainRepository.settingsWhatsappUpdatesApi(
                token,
                booking_id
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                whatsappUpdatesResponse.postValue(response.body())
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











    fun settingsSmsEmailUpdatesApi(token: String,
                                   booking_id: String
    ) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.settingsSmsEmailUpdatesApi(
                    token,
                    booking_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    smsEmailUpdatesResponse.postValue(response.body())
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