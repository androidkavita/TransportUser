package com.govahan.com.activities.contactus

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.contactusmodel.ContactUsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContactUsViewModel  @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val contactUsResponse = MutableLiveData<ContactUsModel>()
    val progressBarStatus = MutableLiveData<Boolean>()

    fun contactUsApi(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.contactUsApi(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                contactUsResponse.postValue(response.body())
            } else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }
}