package com.govahan.com.activities.authorizedfranchise


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.govahan.com.data.MainRepository
import com.govahan.com.model.AuthorizedFranchiseDetailsApi
import com.govahan.com.model.authorisedFranchisesDisttListModel.AuthorisedFranchisesDisttListResponseModel
import com.govahan.com.model.authorisedFranchisesPincodeListModel.AuthorisedFranchisesPinCodeListResponseModel
import com.govahan.com.model.authorisedFranchisesStateListModel.AuthorisedFranchisesStateListResponseModel
import com.govahan.com.model.authorizedfranchisesmodel.AuthorizedFranchisesResponseModel
import com.govahan.com.model.searchauthorisedfranchisesmodel.SearchAuthorisedFranchisesResponseModel
import com.govahan.com.util.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class AuthorizedFranchisesViewModel @Inject constructor(private val mainRepository: MainRepository, private val utils : Utils, @ApplicationContext val context: Context): ViewModel() {

    val progressBarStatus = MutableLiveData<Boolean>()
    val getAuthorizedFranchisesResponseModel = MutableLiveData<AuthorizedFranchisesResponseModel>()
    val searchAuthorisedFranchisesResponseModel = MutableLiveData<SearchAuthorisedFranchisesResponseModel>()
    val AuthorisedFranchisesDetailResponse = MutableLiveData<AuthorizedFranchiseDetailsApi>()


    val franchisesStateListResponse = MutableLiveData<AuthorisedFranchisesStateListResponseModel>()
    val franchisesDisttListResponse = MutableLiveData<AuthorisedFranchisesDisttListResponseModel>()
    val franchisesPincodeListResponse = MutableLiveData<AuthorisedFranchisesPinCodeListResponseModel>()





    fun getAuthorizedFranchisesApi(token: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.getAuthorizedFranchisesApi(
                    token
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
//                Log.d("TAG", response.body().toString())
                    getAuthorizedFranchisesResponseModel.postValue(response.body())
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

    fun vendor_number_vehicle_list(token: String,id:String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.vendor_number_vehicle_list(
                    token,id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    AuthorisedFranchisesDetailResponse.postValue(response.body())
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




    fun searchAuthorizedFranchisesApi(token: String, state_id: String, district_id: String, pin_code: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.searchAuthorizedFranchisesApi(
                    token,state_id, district_id,pin_code
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    searchAuthorisedFranchisesResponseModel.postValue(response.body())
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










    fun getAuthorisedFranchisesStateListApi(token: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.getAuthorisedFranchisesStateListApi(
                    token
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    franchisesStateListResponse.postValue(response.body())
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




    fun getAuthorisedFranchisesDisttListApi(token: String, state_id: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.getAuthorisedFranchisesDisttListApi(
                    token, state_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    franchisesDisttListResponse.postValue(response.body())
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



    fun getAuthorisedFranchisesPincodeListApi(token: String, city_id: String) {
        progressBarStatus.value = true
        try {
            viewModelScope.launch {
                val response = mainRepository.getAuthorisedFranchisesPincodeListApi(
                    token, city_id
                )
                if (response.isSuccessful) {
                    progressBarStatus.value = false
                    franchisesPincodeListResponse.postValue(response.body())
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