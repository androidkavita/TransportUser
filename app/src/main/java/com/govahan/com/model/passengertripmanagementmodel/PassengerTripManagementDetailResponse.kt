package com.govahan.com.model.passengertripmanagementmodel

import com.google.gson.annotations.SerializedName


data class PassengerTripManagementDetailResponse (


    @SerializedName("status"             ) var status           : Int?            = null,
    @SerializedName("message"            ) var message          : String?         = null,
    @SerializedName("data"               ) var data             : ArrayList<PassengerTripManagementDetailData> = arrayListOf(),
    @SerializedName("ride_cancel_status" ) var rideCancelStatus : Int?            = null

            /*@SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<PassengerTripManagementDetailData> = arrayListOf(),
    @SerializedName("ride_cancel_status" ) var rideCancelStatus : Int?     = null */


)

data class PassengerTripManagementDetailData (

    @SerializedName("id"                   ) var id                 : Int?    = null,
    @SerializedName("booking_id"           ) var bookingId          : String? = null,
    @SerializedName("user_id"              ) var userId             : String? = null,
    @SerializedName("vechicle_id"          ) var vechicleId         : String? = null,
    @SerializedName("driver_id"            ) var driverId           : Int?    = null,
    @SerializedName("picup_location"       ) var picupLocation      : String? = null,
    @SerializedName("picup_lat"            ) var picupLat           : String? = null,
    @SerializedName("picup_long"           ) var picupLong          : String? = null,
    @SerializedName("drop_location"        ) var dropLocation       : String? = null,
    @SerializedName("drop_lat"             ) var dropLat            : String? = null,
    @SerializedName("drop_long"            ) var dropLong           : String? = null,
    @SerializedName("fare"                 ) var fare               : String? = null,
    @SerializedName("booking_date"         ) var bookingDate        : String? = null,
    @SerializedName("booking_time"         ) var bookingTime        : String? = null,
    @SerializedName("payment_mode"         ) var paymentMode        : String? = null,
    @SerializedName("booking_status"       ) var bookingStatus      : String? = null,
    @SerializedName("assigned_driver_id"   ) var assignedDriverId   : String? = null,
    @SerializedName("cancelation_reason"   ) var cancelationReason  : String? = null,
    @SerializedName("payment_status"       ) var paymentStatus      : String? = null,
    @SerializedName("invoice_number"       ) var invoiceNumber      : String? = null,
    @SerializedName("transaction_id"       ) var transactionId      : String? = null,
    @SerializedName("currency"             ) var currency           : String? = null,
    @SerializedName("distance"             ) var distance           : Int?    = null,
    @SerializedName("body_type"            ) var bodyType           : String? = null,
    @SerializedName("vehicle_numbers"      ) var vehicleNumbers     : String? = null,
    @SerializedName("capacity"             ) var capacity           : String? = null,
    @SerializedName("created_at"           ) var createdAt          : String? = null,
    @SerializedName("updated_at"           ) var updatedAt          : String? = null,
    @SerializedName("driver_name"          ) var driverName         : String? = null,
    @SerializedName("driver_mobile_number" ) var driverMobileNumber : String? = null,
    @SerializedName("v_id"                 ) var vId                : Int?    = null,
    @SerializedName("main_image"           ) var mainImage          : String? = null,
    @SerializedName("owner_name"           ) var ownerName          : String? = null,
    @SerializedName("owner_id"             ) var ownerId            : Int?    = null,
    @SerializedName("vehicle_name"         ) var vehicleName        : String? = null,
    @SerializedName("bodytype"             ) var bodytype           : String? = null,
    @SerializedName("vehicle_number"       ) var vehicleNumber      : String? = null,
    @SerializedName("rating"               ) var rating             : Int?    = null,
    @SerializedName("start_code"               ) var startCode             : String?    = null
)