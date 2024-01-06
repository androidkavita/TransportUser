package com.govahan.com.activities.passengers.passengervailablevehicle

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.searchPassengerVehicle.SearchPassengerVehicleResponseModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class AvailablePassengerVehiclesViewModel  @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel() {

    var availablePassengerVehicleListResponse = MutableLiveData<SearchPassengerVehicleResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()

    fun searchPassengerVehicleApi(token: String,
                            pick_up_lat: String,
                            pick_up_long: String,
                            drop_lat: String,
                            drop_long: String,
                                  vehicle_type: String,

                                  tyers: String,
                             booking_date: String,
                                  booking_time: String,
                                  pickup_location : String,
                                  dropup_location : String,) {
        progressBarStatus.value = true
        try {
        viewModelScope.launch {
            val response = mainRepository.searchPassengerVehicleApi(
                token,
                pick_up_lat,
                pick_up_long,
                drop_lat,
                drop_long,
                vehicle_type,

                tyers,
                booking_date,booking_time,
                pickup_location,
                dropup_location,


            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                availablePassengerVehicleListResponse.postValue(response.body())
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