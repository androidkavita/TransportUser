package com.govahan.com.model.bookingloadermodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BookingLoaderResponseModel (

    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var bookingLoaderData    : BookingLoaderData?   = BookingLoaderData(),
    @SerializedName("user"    ) var bookingLoaderUser    : BookingLoaderUser?   = BookingLoaderUser(),
    @SerializedName("driver"  ) var bookingLoaderDriver  : BookingLoaderDriver? = BookingLoaderDriver(),
    @SerializedName("ride_code" ) var bookingLoaderrideCode : String? = null

): Serializable

data class BookingLoaderData (


    @SerializedName("id"              ) var id             : Int?    = null,
    @SerializedName("booking_id"      ) var bookingId      : String? = null,
    @SerializedName("picup_location"  ) var picupLocation  : String? = null,
    @SerializedName("drop_location"   ) var dropLocation   : String? = null,
    @SerializedName("fare"            ) var fare           : String? = null,
    @SerializedName("booking_status"  ) var bookingStatus  : String? = null,
    @SerializedName("created_at"      ) var createdAt      : String? = null,
    @SerializedName("vechicle_id"     ) var vechicleId     : String? = null,
    @SerializedName("user_id"         ) var userId         : String? = null,
    @SerializedName("driver_id"       ) var driverId       : Int?    = null,
    @SerializedName("start_code"      ) var startCode      : String? = null,
    @SerializedName("image"           ) var image          : String? = null,
    @SerializedName("body_type"       ) var bodyType       : String? = null,
    @SerializedName("booking_time"    ) var bookingTime    : String? = null,
    @SerializedName("booking_date"    ) var bookingDate    : String? = null,
    @SerializedName("rating"          ) var rating         : String? = null,
    @SerializedName("capacity"        ) var capacity       : String? = null,
    @SerializedName("distance"        ) var distance       : String? = null,
    @SerializedName("vehicle_numbers" ) var vehicleNumbers : String? = null,
    @SerializedName("vehicle_name" ) var vehicle_name : String? = null,
    @SerializedName("vehicle_owner_name" ) var vehicle_owner_name : String? = null,
    @SerializedName("vehicle_type" ) var vehicle_type : String? = null,
    @SerializedName("no_of_tyres" ) var no_of_tyres : String? = null,
    @SerializedName("available" ) var available : String? = null,


    /*@SerializedName("id"                 ) var id                : Int?    = null,
    @SerializedName("booking_id"         ) var bookingId         : String? = null,
    @SerializedName("user_id"            ) var userId            : String? = null,
    @SerializedName("vechicle_id"        ) var vechicleId        : String? = null,
    @SerializedName("driver_id"          ) var driverId          : Int?    = null,
    @SerializedName("picup_location"     ) var picupLocation     : String? = null,
    @SerializedName("picup_lat"          ) var picupLat          : String? = null,
    @SerializedName("picup_long"         ) var picupLong         : String? = null,
    @SerializedName("drop_location"      ) var dropLocation      : String? = null,
    @SerializedName("drop_lat"           ) var dropLat           : String? = null,
    @SerializedName("drop_long"          ) var dropLong          : String? = null,
    @SerializedName("fare"               ) var fare              : String? = null,
    @SerializedName("booking_date"       ) var bookingDate       : String? = null,
    @SerializedName("booking_time"       ) var bookingTime       : String? = null,
    @SerializedName("payment_mode"       ) var paymentMode       : String? = null,
    @SerializedName("booking_status"     ) var bookingStatus     : String? = null,
    @SerializedName("assigned_driver_id" ) var assignedDriverId  : String? = null,
    @SerializedName("cancelation_reason" ) var cancelationReason : String? = null,
    @SerializedName("payment_status"     ) var paymentStatus     : String? = null,
    @SerializedName("invoice_number"     ) var invoiceNumber     : String? = null,
    @SerializedName("created_at"         ) var createdAt         : String? = null,
    @SerializedName("updated_at"         ) var updatedAt         : String? = null*/

): Serializable

data class BookingLoaderUser (

    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null

): Serializable

data class BookingLoaderDriver (

    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null

): Serializable




