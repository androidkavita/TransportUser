package com.govahan.com.model.loaderRideCompletedModel

import com.google.gson.annotations.SerializedName


data class LoaderRideCompletedResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<RideCompletedData> = arrayListOf()

)

data class RideCompletedData (

    @SerializedName("picup_location" ) var picupLocation : String? = null,
    @SerializedName("drop_location"  ) var dropLocation  : String? = null,
    @SerializedName("payment_mode"   ) var paymentMode   : String? = null,
    @SerializedName("fare"           ) var fare          : String? = null,
    @SerializedName("profile_image"  ) var profileImage  : String? = null

)