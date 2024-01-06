package com.govahan.com.model.searchauthorisedfranchisesmodel

import com.google.gson.annotations.SerializedName


data class SearchAuthorisedFranchisesResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var searchfranchisedata    : ArrayList<SearchAuthorisedFranchisesData> = arrayListOf()


)

data class SearchAuthorisedFranchisesData (


    @SerializedName("id"          ) var id         : Int?    = null,
    @SerializedName("owner_name"  ) var ownerName  : String? = null,
    @SerializedName("center_name" ) var centerName : String? = null,
    @SerializedName("mobile_no"   ) var mobileNo   : String? = null,
    @SerializedName("email_id"    ) var emailId    : String? = null,
    @SerializedName("state_id"    ) var stateId    : Int?    = null,
    @SerializedName("district_id" ) var districtId : Int?    = null,
    @SerializedName("pin_code"    ) var pinCode    : Int?    = null,
    @SerializedName("status"      ) var status     : Int?    = null,
    @SerializedName("created_at"  ) var createdAt  : String? = null,
    @SerializedName("updated_at"  ) var updatedAt  : String? = null,
    @SerializedName("logo"  ) var logo  : String? = null
    /*@SerializedName("id"          ) var id         : Int?    = null,
    @SerializedName("owner_name"  ) var ownerName  : String? = null,
    @SerializedName("center_name" ) var centerName : String? = null,
    @SerializedName("mobile_no"   ) var mobileNo   : String? = null,
    @SerializedName("email_id"    ) var emailId    : String? = null,
    @SerializedName("state_id"    ) var stateId    : Int?    = null,
    @SerializedName("district_id" ) var districtId : Int?    = null,
    @SerializedName("pin_code"    ) var pinCode    : Int?    = null,
    @SerializedName("status"      ) var status     : Int?    = null,
    @SerializedName("created_at"  ) var createdAt  : String? = null,
    @SerializedName("updated_at"  ) var updatedAt  : String? = null*/

)