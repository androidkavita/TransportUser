package com.govahan.com.model.loaderAddRaiseComplaintModel

import com.google.gson.annotations.SerializedName


data class LoaderAddRaiseComplaintResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null

)