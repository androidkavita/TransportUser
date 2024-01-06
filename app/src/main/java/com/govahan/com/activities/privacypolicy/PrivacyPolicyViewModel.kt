package com.govahan.com.activities.privacypolicy

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.getPrivacyPolicy.PrivacyPolicyModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PrivacyPolicyViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val privacyPolicyResponse = MutableLiveData<PrivacyPolicyModel>()
    val progressBarStatus = MutableLiveData<Boolean>()

    fun privacyPolicyApi(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.privacyPolicyApi(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                privacyPolicyResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }
    fun aboutUs(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.aboutUs(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                privacyPolicyResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }
  fun calcelation_refund_policy(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.calcelation_refund_policy(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                privacyPolicyResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }

    fun termsAndConditions(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.termsAndConditions(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                privacyPolicyResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }
}