package com.govahan.com.model.passengerinvoicelistmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class PassengerInvoiceListResponseModel (

    /*@SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<PassengerInvoiceData> = arrayListOf()*/
    @SerializedName("status"  ) var status  : Int?            = null,
@SerializedName("message" ) var message : String?         = null,
@SerializedName("data"    ) var data    : ArrayList<PassengerInvoiceData> = arrayListOf()

)@Parcelize

data class PassengerInvoiceData (

    @SerializedName("picup_location" ) var picupLocation : String? = null,
    @SerializedName("drop_location"  ) var dropLocation  : String? = null,
    @SerializedName("booking_id"     ) var bookingId     : String? = null,
    @SerializedName("booking_date"   ) var bookingDate   : String? = null,
    @SerializedName("booking_time"   ) var bookingTime   : String? = null,
    @SerializedName("invoice_number" ) var invoiceNumber : String? = null

): Parcelable