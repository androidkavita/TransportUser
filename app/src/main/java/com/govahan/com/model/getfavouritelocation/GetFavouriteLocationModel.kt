package com.govahan.com.model.getfavouritelocation

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class GetFavouriteLocationModel (

    /*@SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<GetFavouriteLocationData> = arrayListOf()*/

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var getFavLocdata    : ArrayList<GetFavouriteLocationData> = arrayListOf()
)@Parcelize


data class GetFavouriteLocationData (

    /*@SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("user_id"      ) var userId      : Int?    = null,
    @SerializedName("pick_lat"     ) var pickLat     : String? = null,
    @SerializedName("pick_long"    ) var pickLong    : String? = null,
    @SerializedName("pick_address" ) var pickAddress : String? = null,
    @SerializedName("drop_lat"     ) var dropLat     : String? = null,
    @SerializedName("drop_long"    ) var dropLong    : String? = null,
    @SerializedName("drop_address" ) var dropAddress : String? = null,
    @SerializedName("create_at"    ) var createAt    : String? = null,
    @SerializedName("update_at"    ) var updateAt    : String? = null*/


    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("user_id"      ) var userId      : Int?    = null,
    @SerializedName("pick_lat"     ) var pickLat     : String? = null,
    @SerializedName("pick_long"    ) var pickLong    : String? = null,
    @SerializedName("pick_address" ) var pickAddress : String? = null,
    @SerializedName("drop_lat"     ) var dropLat     : String? = null,
    @SerializedName("drop_long"    ) var dropLong    : String? = null,
    @SerializedName("drop_address" ) var dropAddress : String? = null,
    @SerializedName("create_at"    ) var createAt    : String? = null,
    @SerializedName("update_at"    ) var updateAt    : String? = null



): Parcelable