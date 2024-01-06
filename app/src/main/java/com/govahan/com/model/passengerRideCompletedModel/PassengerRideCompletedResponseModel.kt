package com.govahan.com.model.passengerRideCompletedModel

import com.google.gson.annotations.SerializedName


data class PassengerRideCompletedResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<PassengerRideCompletedData> = arrayListOf()

)
data class PassengerRideCompletedData (

    @SerializedName("picup_location" ) var picupLocation : String? = null,
    @SerializedName("drop_location"  ) var dropLocation  : String? = null,
    @SerializedName("payment_mode"   ) var paymentMode   : String? = null,
    @SerializedName("fare"           ) var fare          : String? = null,
    @SerializedName("profile_image"  ) var profileImage  : String? = null

)