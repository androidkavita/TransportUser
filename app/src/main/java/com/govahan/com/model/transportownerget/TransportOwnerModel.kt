package com.govahan.com.model.transportownerget

import com.google.gson.annotations.SerializedName


data class TransportOwnerModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<OwnerData> = arrayListOf()

)


data class OwnerData (

    @SerializedName("name"                  ) var name                : String? = null,
    @SerializedName("mobile_number"         ) var mobileNumber        : String? = null,
    @SerializedName("transporter_loads"     ) var transporterLoads    : String? = null,
    @SerializedName("total_completed_trips" ) var totalCompletedTrips : String? = null

)