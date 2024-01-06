package com.govahan.com.activities.availablevehicles

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.searchvehiclemodel.SearchVehicleResponseModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class AvailableVehiclesViewModel  @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel() {

    var availableVehicleListResponse = MutableLiveData<SearchVehicleResponseModel>()
    val progressBarStatus = MutableLiveData<Boolean>()

    fun searchLoaderVehicleApi(token: String,
                         task: String,
                            pick_up_location: String,
                            pick_up_lat: String,
                            pick_up_long: String,
                            drop_location: String,
                            drop_lat: String,
                            drop_long: String,
                            truck_type: String,
                             capacity: String,
                             body_type: String,
                             wheel: String,

                             booking_date: String,
                               booking_time: String,
                            ) {
        progressBarStatus.value = true
        try {
        viewModelScope.launch {
            val response = mainRepository.searchLoaderVehicleApi(
                token,
                task,
                pick_up_location,
                pick_up_lat,
                pick_up_long,
                drop_location,
                drop_lat,
                drop_long,
                truck_type,
                capacity,
                body_type,
                wheel,
                booking_date,
                booking_time,



            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                availableVehicleListResponse.postValue(response.body())
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