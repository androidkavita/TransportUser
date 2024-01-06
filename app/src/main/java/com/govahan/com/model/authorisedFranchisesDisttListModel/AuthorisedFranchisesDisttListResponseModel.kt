package com.govahan.com.model.authorisedFranchisesDisttListModel

import com.google.gson.annotations.SerializedName


data class AuthorisedFranchisesDisttListResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<FranchisesDisttData> = arrayListOf()

)


data class FranchisesDisttData (

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("state_id"   ) var stateId   : Int?    = null,
    @SerializedName("name"       ) var name      : String? = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null

)