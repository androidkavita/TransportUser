package com.govahan.com.model.loaderongoinghistorydetailmodel



data class LoaderOngoingHistoryDetailResponseModel (
    val `data`: List<OngoingHistoryData>,
    val message: String,
    val ownerDetails: OngoingHistoryOwnerDetails,
    val status: Int,
    val userDetails: OngoingHistoryUserDetails
)

data class OngoingHistoryData(
    val assigned_driver_id: Any,
    val body_type: String,
    val bodyname: String,
    val booking_date: String,
    val booking_id: String,
    val booking_status: String,
    val booking_time: String,
    val cancelation_reason: Any,
    val capacity: String,
    val created_at: String,
    val currency: String,
    val distance: String,
    val driver_id: Int,
    val drop_lat: String,
    val drop_location: String,
    val drop_long: String,
    val fare: String,
    val id: Int,
    val invoice_number: String,
    val payment_mode: String,
    val payment_status: String,
    val picup_lat: String,
    val picup_location: String,
    val picup_long: String,
    val transaction_id: String,
    val updated_at: String,
    val user_id: String,
    val vechicle_id: String,
    val vehicle_name: String,
    val vehicle_number: String,
    val vehicle_numbers: String,
    val vehicle_owner_name: String,
    val driver_name: String,
    val year_of_model: Any
)

data class OngoingHistoryOwnerDetails(
    val email: String,
    val mobile: String,
    val name: String
)

data class OngoingHistoryUserDetails(
    val email: String,
    val mobile_number: String,
    val name: String,
    val v_id: Int
)




/*
data class LoaderOngoingHistoryDetailResponseModel (

    @SerializedName("status"       ) var status       : Int?            = null,
    @SerializedName("message"      ) var message      : String?         = null,
    @SerializedName("data"         ) var data         : ArrayList<OngoingHistoryData> = arrayListOf(),
    @SerializedName("ownerDetails" ) var ownerDetails : OngoingHistoryOwnerDetails?   = OngoingHistoryOwnerDetails(),
    @SerializedName("userDetails"  ) var userDetails  : OngoingHistoryUserDetails?    = OngoingHistoryUserDetails()

)

data class OngoingHistoryData (

    @SerializedName("id"                 ) var id                : Int?    = null,
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
    @SerializedName("body_type"          ) var bodyType          : String? = null,
    @SerializedName("capacity"           ) var capacity          : String? = null,
    @SerializedName("distance"           ) var distance          : String? = null,
    @SerializedName("vehicle_numbers"    ) var vehicleNumbers    : String? = null,
    @SerializedName("booking_time"       ) var bookingTime       : String? = null,
    @SerializedName("payment_mode"       ) var paymentMode       : String? = null,
    @SerializedName("booking_status"     ) var bookingStatus     : String? = null,
    @SerializedName("assigned_driver_id" ) var assignedDriverId  : String? = null,
    @SerializedName("cancelation_reason" ) var cancelationReason : String? = null,
    @SerializedName("payment_status"     ) var paymentStatus     : String? = null,
    @SerializedName("invoice_number"     ) var invoiceNumber     : String? = null,
    @SerializedName("transaction_id"     ) var transactionId     : String? = null,
    @SerializedName("currency"           ) var currency          : String? = null,
    @SerializedName("created_at"         ) var createdAt         : String? = null,
    @SerializedName("updated_at"         ) var updatedAt         : String? = null,
    @SerializedName("vehicle_name"       ) var vehicleName       : String? = null,
    @SerializedName("year_of_model"      ) var yearOfModel       : String? = null,
    @SerializedName("vehicle_number"     ) var vehicleNumber     : String? = null,
    @SerializedName("bodyname"           ) var bodyname          : String? = null

)


data class OngoingHistoryOwnerDetails (

    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("email"  ) var email  : String? = null,
    @SerializedName("mobile" ) var mobile : String? = null

)



data class OngoingHistoryUserDetails (

    @SerializedName("v_id"          ) var vId          : Int?    = null,
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null

)*/
