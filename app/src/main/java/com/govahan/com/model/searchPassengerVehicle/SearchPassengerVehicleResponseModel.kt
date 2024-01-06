package com.govahan.com.model.searchPassengerVehicle

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SearchPassengerVehicleResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<SearchPassengerData> = arrayListOf()

)

@Parcelize
data class SearchPassengerData (


    // 15nov
    @SerializedName("pickup_lat"      ) var pickupLat      : String? = null,
    @SerializedName("pickup_long"     ) var pickupLong     : String? = null,
    @SerializedName("dropup_lat"      ) var dropupLat      : String? = null,
    @SerializedName("dropup_long"     ) var dropupLong     : String? = null,
    @SerializedName("task"            ) var task           : String? = null,
    @SerializedName("picup_location"  ) var picupLocation  : String? = null,
    @SerializedName("dropup_location" ) var dropupLocation : String? = null,
    @SerializedName("distance"        ) var distance       : String? = null,
    @SerializedName("total_fare"      ) var totalFare      : String? = null,
    @SerializedName("driver_id"       ) var driverId       : Int?    = null,
    @SerializedName("from_trip"       ) var fromTrip       : String? = null,
    @SerializedName("to_trip"         ) var toTrip         : String? = null,
    @SerializedName("owner_name"      ) var ownerName      : String? = null,
    @SerializedName("driver_total_booking"      ) var driver_total_booking      : Int? = null,
    @SerializedName("id"              ) var id             : Int?    = null,
    @SerializedName("vehicle_name"    ) var vehicleName    : String? = null,
    @SerializedName("year_model"      ) var yearModel      : Int?    = null,
    @SerializedName("vehicle_no"      ) var vehicleNo      : String? = null,
    @SerializedName("seat"            ) var seat           : Int?    = null,
    @SerializedName("no_tyres"        ) var noTyres        : Int?    = null,
    @SerializedName("driver_name"     ) var driverName     : String? = null,
    @SerializedName("mobile_number"   ) var mobileNumber   : String? = null,
    @SerializedName("v_id"            ) var vId            : String? = null,
    @SerializedName("vehicle_id"      ) var vehicleId      : Int?    = null,
    @SerializedName("booking_date"    ) var bookingDate    : String? = null,
    @SerializedName("booking_time"    ) var bookingTime    : String? = null,
    @SerializedName("available"       ) var available      : String? = null,
    @SerializedName("main_image"       ) var mainImage      : String? = null,
    @SerializedName("bodytype"       ) var bodytype      : String? = null,
    @SerializedName("owner_id"           ) var ownerId          : Int?    = null,
    @SerializedName("rating"             ) var rating           : Int?    = null,
    @SerializedName("vehicle_number"     ) var vehicleNumber    : String? = null


) : Parcelable




/*
// @20oct

@SerializedName("pickup_lat"         ) var pickupLat        : String? = null,
@SerializedName("pickup_long"        ) var pickupLong       : String? = null,
@SerializedName("dropup_lat"         ) var dropupLat        : String? = null,
@SerializedName("dropup_long"        ) var dropupLong       : String? = null,
@SerializedName("task"               ) var task             : String? = null,
@SerializedName("picup_location"     ) var picupLocation    : String? = null,
@SerializedName("dropup_location"    ) var dropupLocation   : String? = null,
@SerializedName("distance"           ) var distance         : String? = null,
@SerializedName("total_fare"         ) var totalFare        : String? = null,
@SerializedName("driver_id"          ) var driverId         : Int?    = null,
@SerializedName("from_trip"          ) var fromTrip         : String? = null,
@SerializedName("to_trip"            ) var toTrip           : String? = null,
@SerializedName("vehicle_owner_name" ) var vehicleOwnerName : String? = null,
@SerializedName("id"                 ) var id               : Int?    = null,
@SerializedName("vehicle_name"       ) var vehicleName      : String? = null,
@SerializedName("year_of_model"      ) var yearOfModel      : Int?    = null,
@SerializedName("vehicle_number"     ) var vehicleNumber    : String? = null,
@SerializedName("no_tyres"        ) var noTyres        : Int?    = null,
@SerializedName("seat"           ) var seat         : String? = null,
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
@SerializedName("rating"             ) var rating           : Int?    = null
*/


/*@SerializedName("vehicle_id"   ) var vehicleId   : Int?    = null,
    @SerializedName("vehicle_name" ) var vehicleName : String? = null,
    @SerializedName("vehicle_no"   ) var vehicleNo   : String? = null,
    @SerializedName("available"    ) var available   : Int?    = null,
    @SerializedName("driver_id"    ) var driverId    : Int?    = null,
    @SerializedName("name"         ) var name        : String? = null,
    @SerializedName("v_id"         ) var vId         : Int?    = null,
    @SerializedName("model"        ) var model       : String? = null,
    @SerializedName("v_type"       ) var vType       : String? = null,
    @SerializedName("seat"         ) var seat        : Int?    = null,
    @SerializedName("v_color"      ) var vColor      : String? = null,
    @SerializedName("wheel"        ) var wheel       : String? = null,
    @SerializedName("from_address" ) var fromAddress : String? = null,
    @SerializedName("to_address"   ) var toAddress   : String? = null,
    @SerializedName("booking_date" ) var bookingDate : String? = null,
    @SerializedName("booking_time" ) var bookingTime : String? = null,
    @SerializedName("distance"     ) var distance    : String? = null,
    @SerializedName("final_fare"   ) var finalFare   : String? = null,
    @SerializedName("rating"       ) var rating      : String? = null,
    @SerializedName("pickup_lat"   ) var pickupLat   : String? = null,
    @SerializedName("pickup_long"  ) var pickupLong  : String? = null,
    @SerializedName("dropup_lat"   ) var dropupLat   : String? = null,
    @SerializedName("dropup_long"  ) var dropupLong  : String? = null,
    @SerializedName("image"        ) var image       : String? = null,
    @SerializedName("owner_name"   ) var ownerName   : String? = null,
    @SerializedName("owner_id"     ) var ownerId     : Int?    = null*/