package com.govahan.com.model.searchvehiclemodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SearchVehicleResponseModel (

    @SerializedName("status"  ) var status  : Int?            = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<SearchVehicleData> = arrayListOf()

)
@Parcelize
data class SearchVehicleData (


    @SerializedName("id"                 ) var id               : Int?    = null,
    @SerializedName("driver_id"          ) var driverId         : Int?    = null,
    @SerializedName("vehicle_id"          ) var vehicle_id         : Int?    = null,
    @SerializedName("vehicle_owner_name" ) var vehicleOwnerName : String? = null,
    @SerializedName("vehicle_name"       ) var vehicleName      : String? = null,
    @SerializedName("year_of_model"      ) var yearOfModel      : Int?    = null,
    @SerializedName("vehicle_number"     ) var vehicleNumber    : String? = null,
    @SerializedName("vehicle_type"       ) var vehicleType      : Int?    = null,
    @SerializedName("vehicle_category"   ) var vehicleCategory  : Int?    = null,
    @SerializedName("rating"   ) var rating  : Int?    = null,
    @SerializedName("capacity"           ) var capacity         : String? = null,
    @SerializedName("height"             ) var height           : Int?    = null,
    @SerializedName("color"              ) var color            : Int?    = null,
    @SerializedName("no_of_tyres"        ) var noOfTyres        : Int?    = null,
    @SerializedName("avl_time"        ) var     avl_time    : String?    = null,
   // @SerializedName("body_type"          ) var bodyType         : Int?    = null,
    @SerializedName("doc_status"         ) var docStatus        : Int?    = null,
    @SerializedName("status"             ) var status           : Int?    = null,
    @SerializedName("created_at"         ) var createdAt        : String? = null,
    @SerializedName("updated_at"         ) var updatedAt        : String? = null,
    @SerializedName("driver_name"        ) var driverName       : String? = null,
    @SerializedName("v_id"               ) var vId              : Int?    = null,
    @SerializedName("bodytype"           ) var bodytype         : String? = null,
    @SerializedName("task"               ) var task             : String? = null,
    @SerializedName("pickup_lat"         ) var pickupLat        : String? = null,
    @SerializedName("pickup_long"        ) var pickupLong       : String? = null,
    @SerializedName("dropup_lat"         ) var dropupLat        : String? = null,
    @SerializedName("dropup_long"        ) var dropupLong       : String? = null,
    @SerializedName("available"          ) var available        : String? = null,
    @SerializedName("wheel"              ) var wheel            : String? = null,
    @SerializedName("distance"     ) var distance    : String? = null,
    @SerializedName("total_fare"         ) var totalFare        : String? = null,
    @SerializedName("driver_total_booking"         ) var driver_total_booking        : Int? = null,
    @SerializedName("picup_location"     ) var picupLocation    : String? = null,
    @SerializedName("dropup_location"    ) var dropupLocation   : String? = null,
    @SerializedName("main_image"         ) var mainImage        : String? = null,
    @SerializedName("owner_name"         ) var ownerName        : String? = null,
    @SerializedName("owner_id"           ) var ownerId          : Int?    = null,
    @SerializedName("mobile_number"           ) var mobile_number          : String?    = null,
    @SerializedName("booking_date"        ) var bookingDate        : String? = null,
    @SerializedName("booking_time"        ) var bookingTime        : String? = null

   /* @SerializedName("id"                  ) var id                 : Int?    = null,
    @SerializedName("user_id"             ) var userId             : String? = null,
    @SerializedName("loader_name"         ) var loaderName         : String? = null,
    @SerializedName("registration_number" ) var registrationNumber : String? = null,
    @SerializedName("loader_number"       ) var loaderNumber       : String? = null,
    @SerializedName("wheel_id"            ) var wheelId            : Int?    = null,
    @SerializedName("capacity_id"         ) var capacityId         : String? = null,
    @SerializedName("height_id"           ) var heightId           : String? = null,
    @SerializedName("body_type_id"        ) var bodyTypeId         : Int?    = null,
    @SerializedName("truck_type_id"       ) var truckTypeId        : Int?    = null,
    @SerializedName("address"             ) var address            : String? = null,
    @SerializedName("add_lat"             ) var addLat             : String? = null,
    @SerializedName("add_long"            ) var addLong            : String? = null,
    @SerializedName("driver_id"           ) var driverId           : String? = null,
    @SerializedName("status"              ) var status             : Int?    = null,
    @SerializedName("contact_number"      ) var contactNumber      : String? = null,
    @SerializedName("main_image"          ) var mainImage          : String? = null,
    @SerializedName("created_at"          ) var createdAt          : String? = null,
    @SerializedName("updated_at"          ) var updatedAt          : String? = null,
    @SerializedName("driver_name"         ) var driverName         : String? = null,
    @SerializedName("bodytype"            ) var bodytype           : String? = null,
    @SerializedName("capacity"            ) var capacity           : String? = null,
    @SerializedName("task"                ) var task               : String? = null,
    @SerializedName("pickup_lat"          ) var pickupLat          : String? = null,
    @SerializedName("pickup_long"         ) var pickupLong         : String? = null,
    @SerializedName("dropup_lat"          ) var dropupLat          : String? = null,
    @SerializedName("dropup_long"         ) var dropupLong         : String? = null,
    @SerializedName("available"           ) var available          : String? = null,
    @SerializedName("wheel"               ) var wheel              : String? = null,
    @SerializedName("total_distance"      ) var totalDistance      : String? = null,
    @SerializedName("total_fare"          ) var totalFare          : String? = null,
    @SerializedName("picup_location"      ) var picupLocation      : String? = null,
    @SerializedName("dropup_location"     ) var dropupLocation     : String? = null,
    @SerializedName("owner_name"          ) var ownerName          : String? = null,
    @SerializedName("booking_date"        ) var bookingDate        : String? = null,
    @SerializedName("booking_time"        ) var bookingTime        : String? = null,
    @SerializedName("distance"            ) var distance           : Double? = null*/


) : Parcelable