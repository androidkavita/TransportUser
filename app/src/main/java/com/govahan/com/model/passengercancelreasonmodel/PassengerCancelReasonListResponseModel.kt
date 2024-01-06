package com.govahan.com.model.passengercancelreasonmodel


import com.google.gson.annotations.SerializedName



data class PassengerCancelReasonListResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<PassengerCancelReasonData> = arrayListOf()

)



data class PassengerCancelReasonData (

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("reason"     ) var reason    : String? = null,
    @SerializedName("status"     ) var status    : Int?    = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null

)