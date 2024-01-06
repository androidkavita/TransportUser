package com.govahan.com.model.settingwhatsappmodel

import com.google.gson.annotations.SerializedName

data class SettingWhatsappResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : String? = null

)