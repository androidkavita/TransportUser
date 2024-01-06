package com.govahan.com.model.trucknooftyreget

import com.google.gson.annotations.SerializedName


data class TruckNoOfTyreModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<TruckNoOfTyreData> = arrayListOf()

)

data class TruckNoOfTyreData (

    @SerializedName("id"    ) var id    : Int?    = null,
    @SerializedName("wheel" ) var wheel : String? = null

)