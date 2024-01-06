package com.govahan.com.activities.myprofile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.getprofile.GetUserProfileModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val userprofileResponse = MutableLiveData<GetUserProfileModel>()
    val progressBarStatus = MutableLiveData<Boolean>()

    fun userProfileApi(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.getUserProfile(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                userprofileResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }

    }

}