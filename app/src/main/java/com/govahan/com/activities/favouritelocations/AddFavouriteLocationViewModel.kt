package com.govahan.com.activities.favouritelocations

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.addfavouritelocation.AddFavouriteLocationModel
import com.govahan.com.model.deletefavlocation.DeleteFavLocationModel
import com.govahan.com.model.getfavouritelocation.GetFavouriteLocationModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class AddFavouriteLocationViewModel  @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context) : ViewModel() {

    var addFavouriteLocationListResponse = MutableLiveData<AddFavouriteLocationModel>()
    val progressBarStatus = MutableLiveData<Boolean>()
    val getFavouriteResponse = MutableLiveData<GetFavouriteLocationModel>()
    val deleteFavLocationResponse = MutableLiveData<DeleteFavLocationModel>()





    fun addFavouriteLocationApi(token: String,
                            pick_up_lat: String,
                            pick_up_long: String,
                            drop_lat: String,
                            drop_long: String) {
        progressBarStatus.value = true
        try {
        viewModelScope.launch {
            val response = mainRepository.addFavouriteLocationApi(
                token,
                pick_up_lat,
                pick_up_long,
                drop_lat,
                drop_long
            )
            if (response.isSuccessful) {
                progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                addFavouriteLocationListResponse.postValue(response.body())
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


    fun getFavouriteLocationApi(token: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.getFavouriteLocationApi(
                    token
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    getFavouriteResponse.postValue(response.body())
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



    fun deleteFavLocationApi(token: String, id: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.deleteFavLocationApi(
                    token,
                    id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    deleteFavLocationResponse.postValue(response.body())
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