package com.govahan.com.model.passengercanceltripmodel

import com.google.gson.annotations.SerializedName

data class PassengerTripCancelResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("CRN"     ) var CRN     : String? = null

)