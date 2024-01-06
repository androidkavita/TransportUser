package com.govahan.com.model.loginResponse

import com.google.gson.annotations.SerializedName


data class LoginResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : Data?   = Data()

)

data class Data (

    @SerializedName("mobile_number" ) var mobileNumber : String? = null,
    @SerializedName("otp"           ) var otp          : String? = null,
    @SerializedName("status"        ) var status       : Int?    = null

)