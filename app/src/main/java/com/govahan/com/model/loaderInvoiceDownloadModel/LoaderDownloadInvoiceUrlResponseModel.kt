package com.govahan.com.model.loaderInvoiceDownloadModel

import com.google.gson.annotations.SerializedName


data class LoaderDownloadInvoiceUrlResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("url"     ) var url     : String? = null

)