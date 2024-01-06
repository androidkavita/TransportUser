package com.govahan.com.fragment.loader

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.truckbodytypeget.TruckBodyTypeModel
import com.govahan.com.model.truckcapacityget.TruckCapacityGetModel
import com.govahan.com.model.trucknooftyreget.TruckNoOfTyreModel
import com.govahan.com.model.truckpricefor_get.TruckPriceForModel
import com.govahan.com.model.trucktypegetmodel.TruckTypeModel
import com.govahan.com.model.vehicletypemodel.GetVehicleTypeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoaderFragmentViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {
    val vehicleTypeListResponse = MutableLiveData<GetVehicleTypeModel>()

     val truckTypeListResponse = MutableLiveData<TruckTypeModel>()
    val truckCapacityListResponse = MutableLiveData<TruckCapacityGetModel>()
    val truckBodyTypeListResponse = MutableLiveData<TruckBodyTypeModel>()
    val truckNoOfTyreListResponse = MutableLiveData<TruckNoOfTyreModel>()
    val truckPriceForListResponse = MutableLiveData<TruckPriceForModel>()
    val progressBarStatus = MutableLiveData<Boolean>()
   // val truckFeetAndTonResponse = MutableLiveData<TruckFeetAndTonModel>()
   val _progressBarVisibility = MutableLiveData<Int>()
    val errorString = MutableLiveData<String>()


   fun truckTypeApi(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.truckTypeApi(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                truckTypeListResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }
    fun vehicleTypeApi(token : String,type:String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.vehicleTypeApi(token,type)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                vehicleTypeListResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }


    fun truckCapacityApi(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.truckCapacityApi(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                truckCapacityListResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }


    fun truckBodyTypeApi(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.truckBodyTypeApi(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                truckBodyTypeListResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }


//    fun truckNoOfTyreApi(token : String) {
//        progressBarStatus.value = true
//        viewModelScope.launch {
//            val response = mainRepository.truckNoOfTyreApi(token)
//            if (response.isSuccessful) {
//                progressBarStatus.value = false
//                truckNoOfTyreListResponse.postValue(response.body())
//            }
//            else {
//                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
//            }
//        }
//    }


    fun truckPriceForApi(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.truckPriceForApi(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                truckPriceForListResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }
















}