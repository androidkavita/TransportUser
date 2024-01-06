package com.govahan.com.model.passengerComplaintlistmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PassengerComplaintListResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<PassengerComplaintData> = arrayListOf()

)@Parcelize



data class PassengerComplaintData (

    @SerializedName("booking_id"     ) var bookingId     : String? = null,
    @SerializedName("picup_location" ) var picupLocation : String? = null,
    @SerializedName("drop_location"  ) var dropLocation  : String? = null,
    @SerializedName("booking_date"   ) var bookingDate   : String? = null,
    @SerializedName("booking_time"   ) var bookingTime   : String? = null,
    @SerializedName("fare"           ) var fare          : String? = null

): Parcelable


