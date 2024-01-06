package com.govahan.com.model.notificationmodel

import com.google.gson.annotations.SerializedName


data class NotificationResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<NotificationData> = arrayListOf()

)



data class NotificationData (

    @SerializedName("id"        ) var id       : Int?    = null,
    @SerializedName("user_id"   ) var userId   : Int?    = null,
    @SerializedName("message"   ) var message  : String? = null,
    @SerializedName("title"   ) var title  : String? = null,
    @SerializedName("create_at" ) var createAt : String? = null,
    @SerializedName("update_at" ) var updateAt : String? = null

)
