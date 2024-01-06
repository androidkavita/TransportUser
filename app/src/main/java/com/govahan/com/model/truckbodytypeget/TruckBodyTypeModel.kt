package com.govahan.com.model.truckbodytypeget

import com.google.gson.annotations.SerializedName

data class TruckBodyTypeModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<TruckBodyTypeData> = arrayListOf()

)

data class TruckBodyTypeData (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null

)