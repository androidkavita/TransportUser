package com.govahan.com.model.noOfTyrePModel

import com.google.gson.annotations.SerializedName


data class GetNoOfTyrePModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<NoOfTyrePData> = arrayListOf()

)

data class NoOfTyrePData (

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("type"       ) var type      : Int?    = null,
    @SerializedName("wheel"      ) var wheel     : String? = null,
    @SerializedName("status"     ) var status    : Int?    = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null

)
