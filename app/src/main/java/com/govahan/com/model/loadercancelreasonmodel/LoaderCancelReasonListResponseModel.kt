package com.govahan.com.model.loadercancelreasonmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class LoaderCancelReasonListResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<LoaderCancelReasonData> = arrayListOf()

)@Parcelize

data class LoaderCancelReasonData (

    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("reason"     ) var reason    : String? = null,
    @SerializedName("status"     ) var status    : Int?    = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null

): Parcelable
