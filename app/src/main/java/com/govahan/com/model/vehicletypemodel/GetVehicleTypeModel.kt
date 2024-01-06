package com.govahan.com.model.vehicletypemodel

import com.google.gson.annotations.SerializedName


data class GetVehicleTypeModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<VehicleTypeData> = arrayListOf()

)

data class VehicleTypeData (

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("v_type"     ) var vType     : String? = null,
    @SerializedName("status"     ) var status    : Int?    = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null

)
