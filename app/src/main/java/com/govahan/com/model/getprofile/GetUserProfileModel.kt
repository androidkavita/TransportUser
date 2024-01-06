package com.govahan.com.model.getprofile

import com.google.gson.annotations.SerializedName


data class GetUserProfileModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : ProfileModelData?   = ProfileModelData()

)

data class ProfileModelData (

    @SerializedName("id"            ) var id           : Int?    = null,
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("device_type"   ) var deviceType   : String? = null,
    @SerializedName("device_token"  ) var deviceToken  : String? = null,
    @SerializedName("address"       ) var address      : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null,
    @SerializedName("profile_image" ) var profileImage : String? = null

)

