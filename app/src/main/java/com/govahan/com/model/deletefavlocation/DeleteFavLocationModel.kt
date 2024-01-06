package com.govahan.com.model.deletefavlocation

import com.google.gson.annotations.SerializedName


data class DeleteFavLocationModel (

    @SerializedName("status"  ) var status  : Int?     = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Boolean? = null

)
