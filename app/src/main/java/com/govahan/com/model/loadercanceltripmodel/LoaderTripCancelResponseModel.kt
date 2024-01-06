package com.govahan.com.model.loadercanceltripmodel

import com.google.gson.annotations.SerializedName


data class LoaderTripCancelResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("CRN"     ) var CRN     : String? = null

)