package com.govahan.com.activities.editprofile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.loginOtpModel.LoginOtpResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel   @Inject constructor(private val mainRepository: MainRepository): ViewModel(){

    val updateUserProfileResponse = MutableLiveData<LoginOtpResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()

    fun updateUserProfile(token : String, name : String ,email : String, mobile : String,
                             address : String, device_token : String, device_type : String, device_id : String, profile_image : MultipartBody.Part?) {

        val userName: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val emailN : RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), email)
        val mobileN : RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), mobile)
        val addressN : RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), address)
        val device_tokenN : RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), device_token)
        val device_typeN : RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), device_type)
        val device_idN : RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), device_id)

        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.updateUserProfile(token , userName,  emailN, mobileN, addressN,  device_tokenN,
                device_typeN, device_idN, profile_image!!)
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                updateUserProfileResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }

    }

}