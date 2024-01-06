package com.govahan.com.model.passengerdownloadinvoicemodel

import com.google.gson.annotations.SerializedName


data class PassengerDownloadInvoiceUrlResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("url"     ) var url     : String? = null

)