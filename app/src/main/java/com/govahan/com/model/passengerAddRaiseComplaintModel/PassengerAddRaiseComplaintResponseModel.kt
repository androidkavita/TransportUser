package com.govahan.com.model.passengerAddRaiseComplaintModel

import com.google.gson.annotations.SerializedName


data class PassengerAddRaiseComplaintResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null

)