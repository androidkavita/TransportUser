package com.govahan.com.model.truckcapacityget

import com.google.gson.annotations.SerializedName


data class TruckCapacityGetModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<TruckCapacityData> = arrayListOf()

)


data class TruckCapacityData (

    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("capacity" ) var capacity : String? = null

)