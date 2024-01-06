package com.govahan.com.model.authorisedFranchisesStateListModel

import com.google.gson.annotations.SerializedName

data class AuthorisedFranchisesStateListResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<FranchisesStateData> = arrayListOf()

)

data class FranchisesStateData (

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("name" ) var stateName : String? = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null

)