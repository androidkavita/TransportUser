package com.govahan.com.model.seatingcapacitymodel

import com.google.gson.annotations.SerializedName

data class GetSeatingCapacityModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<SeatingCapacityData> = arrayListOf()
)

data class SeatingCapacityData (

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("seat"       ) var seat      : Int?    = null,
    @SerializedName("status"     ) var status    : Int?    = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null
)