package com.govahan.com.model.myoffersmodel

import com.google.gson.annotations.SerializedName


data class MyOffersResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<MyOffersData> = arrayListOf()

)


data class MyOffersData (

    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("discount"     ) var discount    : String? = null,
    @SerializedName("offer_images" ) var offerImages : String? = null

)