package com.govahan.com.fragment.passenger

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.noOfTyrePModel.GetNoOfTyrePModel
import com.govahan.com.model.seatingcapacitymodel.GetSeatingCapacityModel
import com.govahan.com.model.vehicletypemodel.GetVehicleTypeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassengerFragmentViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

     val vehicleTypeListResponse = MutableLiveData<GetVehicleTypeModel>()
    val seatingCapacityListResponse = MutableLiveData<GetSeatingCapacityModel>()
    val noOfTyresPListResponse = MutableLiveData<GetNoOfTyrePModel>()
    val progressBarStatus = MutableLiveData<Boolean>()
   // val truckFeetAndTonResponse = MutableLiveData<TruckFeetAndTonModel>()
   val _progressBarVisibility = MutableLiveData<Int>()
    val errorString = MutableLiveData<String>()


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


    fun seatingCapacityApi(token : String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.seatingCapacityApi(token)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                seatingCapacityListResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }




    fun noOfTyrePApi(token : String,type: String) {
        progressBarStatus.value = true
        viewModelScope.launch {
            val response = mainRepository.noOfTyrePApi(token,type)
            if (response.isSuccessful) {
                progressBarStatus.value = false
                noOfTyresPListResponse.postValue(response.body())
            }
            else {
                progressBarStatus.value = false
                Log.d("TAG", response.body().toString())
            }
        }
    }



















}