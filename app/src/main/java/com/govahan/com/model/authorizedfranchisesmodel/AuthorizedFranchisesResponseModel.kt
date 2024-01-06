package com.govahan.com.model.authorizedfranchisesmodel

import com.google.gson.annotations.SerializedName


data class AuthorizedFranchisesResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var franchisealldata    : ArrayList<AuthorizedFranchisesData> = arrayListOf()

)


data class AuthorizedFranchisesData (

    @SerializedName("id"          ) var id         : Int?    = null,
    @SerializedName("frenchise_id"          ) var frenchise_id         : Int?    = null,
    @SerializedName("owner_name"  ) var ownerName  : String? = null,
    @SerializedName("center_name" ) var centerName : String? = null,
    @SerializedName("mobile_no"   ) var mobileNo   : String? = null,
    @SerializedName("logo"   ) var logo   : String? = null,
    @SerializedName("email_id"    ) var emailId    : String? = null,
    @SerializedName("status"      ) var status     : Int?    = null,
    @SerializedName("created_at"  ) var createdAt  : String? = null,
    @SerializedName("updated_at"  ) var updatedAt  : String? = null,
    @SerializedName("mobile_number"  ) var mobile_number  : String? = null,
    @SerializedName("vehicle_numbers"  ) var vehicle_numbers  : String? = null,
    @SerializedName("vehicle_type"  ) var vehicle_type  : String? = null,
    @SerializedName("address"  ) var address  : String? = null,
    @SerializedName("profile_image"  ) var profile_image  : String? = null,
    @SerializedName("email"  ) var email  : String? = null,
    @SerializedName("name"  ) var name  : String? = null,

)