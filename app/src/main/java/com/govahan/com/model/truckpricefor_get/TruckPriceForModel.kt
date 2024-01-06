package com.govahan.com.model.truckpricefor_get

import com.google.gson.annotations.SerializedName


data class TruckPriceForModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<PriceForData> = arrayListOf()

)

data class PriceForData (

    @SerializedName("id"    ) var id    : Int?    = null,
    @SerializedName("wheel" ) var wheel : String? = null

)