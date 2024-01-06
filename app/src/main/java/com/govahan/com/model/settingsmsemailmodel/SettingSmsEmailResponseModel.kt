package com.govahan.com.model.settingsmsemailmodel

import com.google.gson.annotations.SerializedName

data class SettingSmsEmailResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : String? = null

)