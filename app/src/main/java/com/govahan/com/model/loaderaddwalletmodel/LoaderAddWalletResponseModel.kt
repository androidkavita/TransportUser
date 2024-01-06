package com.govahan.com.model.loaderaddwalletmodel

import com.google.gson.annotations.SerializedName


data class LoaderAddWalletResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null

)