package com.govahan.com.model.loaderpaymentsuccessmodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoaderPaymentSuccessResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : LoaderPaymentSuccessData?   = LoaderPaymentSuccessData(),
    @SerializedName("user"    ) var user    : LoaderPaymentSuccessUser?   = LoaderPaymentSuccessUser(),
    @SerializedName("driver"  ) var driver  : LoaderPaymentSuccessDriver? = LoaderPaymentSuccessDriver()

): Serializable



data class LoaderPaymentSuccessData (

    @SerializedName("id"              ) var id             : Int?    = null,
    @SerializedName("booking_id"      ) var bookingId      : String? = null,
    @SerializedName("picup_location"  ) var picupLocation  : String? = null,
    @SerializedName("drop_location"   ) var dropLocation   : String? = null,
    @SerializedName("fare"            ) var fare           : String? = null,
    @SerializedName("booking_status"  ) var bookingStatus  : String? = null,
    @SerializedName("created_at"      ) var createdAt      : String? = null,
    @SerializedName("vechicle_id"     ) var vechicleId     : String? = null,
    @SerializedName("vehicle_name"     ) var vehicle_name     : String? = null,
    @SerializedName("user_id"         ) var userId         : String? = null,
    @SerializedName("driver_id"       ) var driverId       : Int?    = null,
    @SerializedName("image"           ) var image          : String? = null,
    @SerializedName("body_type"       ) var bodyType       : String? = null,
    @SerializedName("booking_time"    ) var bookingTime    : String? = null,
    @SerializedName("booking_date"    ) var bookingDate    : String? = null,
    @SerializedName("rating"          ) var rating         : String? = null,
    @SerializedName("capacity"        ) var capacity       : String? = null,
    @SerializedName("distance"        ) var distance       : String? = null,
    @SerializedName("vehicle_numbers" ) var vehicleNumbers : String? = null,
    @SerializedName("ride_code" ) var ride_code : String? = null,
    @SerializedName("mainImage" ) var mainImage : String? = null,
    @SerializedName("vehicle_owner_name" ) var vehicleOwnerName : String? = null,

): Serializable



data class LoaderPaymentSuccessUser (

    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null

): Serializable



data class LoaderPaymentSuccessDriver (

    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null,

): Serializable