package com.govahan.com.model.bookingreviewget

import com.google.gson.annotations.SerializedName


data class BookingReviewModel (
    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<BookingReviewData> = arrayListOf()
)

data class BookingReviewData (

    @SerializedName("pickup_lat"         ) var pickupLat        : String? = null,
    @SerializedName("pickup_long"        ) var pickupLong       : String? = null,
    @SerializedName("dropup_lat"         ) var dropupLat        : String? = null,
    @SerializedName("dropup_long"        ) var dropupLong       : String? = null,
    @SerializedName("task"               ) var task             : String? = null,
    @SerializedName("load_caring"        ) var loadCaring       : String? = null,
    @SerializedName("picup_location"     ) var picupLocation    : String? = null,
    @SerializedName("dropup_location"    ) var dropupLocation   : String? = null,
    @SerializedName("distance"           ) var distance         : String? = null,
    @SerializedName("total_fare"         ) var totalFare        : String? = null,
    @SerializedName("driver_id"          ) var driverId         : Int?    = null,
    @SerializedName("body_type"          ) var bodyType         : String  ? = null,
    @SerializedName("from_trip"          ) var fromTrip         : String? = null,
    @SerializedName("to_trip"            ) var toTrip           : String? = null,
    @SerializedName("vehicle_owner_name" ) var vehicleOwnerName : String? = null,
    @SerializedName("id"                 ) var id               : Int?    = null,
    @SerializedName("vehicle_name"       ) var vehicleName      : String? = null,
    @SerializedName("year_of_model"      ) var yearOfModel      : Int?    = null,
    @SerializedName("vehicle_number"     ) var vehicleNumber    : String? = null,
    @SerializedName("no_of_tyres"        ) var noOfTyres        : Int?    = null,
    @SerializedName("capacity"           ) var capacity         : String? = null,
    @SerializedName("height"             ) var height           : Int?    = null,
    @SerializedName("bodytype"           ) var bodytype         : String? = null,
    @SerializedName("driver_name"        ) var driverName       : String? = null,
    @SerializedName("mobile_number"      ) var mobileNumber     : String? = null,
    @SerializedName("v_id"               ) var vId              : Int?    = null,
    @SerializedName("vehicle_id"         ) var vehicleId        : Int?    = null,
    @SerializedName("booking_date"       ) var bookingDate      : String? = null,
    @SerializedName("booking_time"       ) var bookingTime      : String? = null,
    @SerializedName("available"          ) var available        : String? = null,
    @SerializedName("main_image"         ) var mainImage        : String? = null,
    @SerializedName("owner_name"         ) var ownerName        : String? = null,
    @SerializedName("owner_id"           ) var ownerId          : Int?    = null,
    @SerializedName("rating"             ) var rating           : Int?    = null,
    @SerializedName("amount_pay"             ) var amount_pay           : Double?    = null,

)
