package com.govahan.com.model.sendmailmodel


import com.google.gson.annotations.SerializedName


data class LoaderSendMailResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null

)