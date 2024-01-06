package com.govahan.com.model.loaderrescheduletripmodel

import com.google.gson.annotations.SerializedName


data class LoaderRescheduleTripResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null

)