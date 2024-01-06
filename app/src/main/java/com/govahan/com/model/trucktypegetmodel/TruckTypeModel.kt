package com.govahan.com.model.trucktypegetmodel

import com.google.gson.annotations.SerializedName


data class TruckTypeModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<TruckTypeData> = arrayListOf()

)


data class TruckTypeData (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null

){
    override fun toString(): String {
        return name.toString()
    }
}