package com.govahan.com.model.passengeraddratingmodel

import com.google.gson.annotations.SerializedName


data class PassengerAddRatingResponseModel (


    @SerializedName("status"  ) var status  : Int?     = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Boolean? = null


)