package com.govahan.com.model.addfavouritelocation

import com.google.gson.annotations.SerializedName

data class AddFavouriteLocationModel (

    @SerializedName("status"  ) var status  : Int?     = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Boolean? = null

)