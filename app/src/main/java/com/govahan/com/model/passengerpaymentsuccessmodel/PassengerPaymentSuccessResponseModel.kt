package com.govahan.com.model.passengerpaymentsuccessmodel

import com.google.gson.annotations.SerializedName

data class PassengerPaymentSuccessResponseModel (


    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : PassengerPaymentSuccessData?   = PassengerPaymentSuccessData(),
    @SerializedName("user"    ) var user    : PassengerPaymentSuccessUser?   = PassengerPaymentSuccessUser(),
    @SerializedName("driver"  ) var driver  : PassengerPaymentSuccessDriver? = PassengerPaymentSuccessDriver()
/* @SerializedName("status"  ) var status  : Int?    = null,
  @SerializedName("message" ) var message : String? = null,
  @SerializedName("data"    ) var data    : Data?   = Data(),
  @SerializedName("user"    ) var user    : User?   = User(),
  @SerializedName("driver"  ) var driver  : Driver? = Driver()*/

)



data class PassengerPaymentSuccessData (

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
    @SerializedName("image"           ) var image          : String? = null,
    @SerializedName("body_type"       ) var bodyType       : String? = null,
    @SerializedName("booking_time"    ) var bookingTime    : String? = null,
    @SerializedName("booking_date"    ) var bookingDate    : String? = null,
    @SerializedName("rating"          ) var rating         : String? = null,
    @SerializedName("capacity"        ) var capacity       : String? = null,
    @SerializedName("seat_no"        ) var seat_no       : String? = null,
    @SerializedName("distance"        ) var distance       : String? = null,
    @SerializedName("vehicle_numbers" ) var vehicleNumbers : String? = null,
    @SerializedName("vehicle_name" ) var vehicle_name : String? = null,
    @SerializedName("ride_code" ) var ride_code : String? = null,

)
data class PassengerPaymentSuccessUser (
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null
)
data class PassengerPaymentSuccessDriver (
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null,
    @SerializedName("vehicle_owner_name" ) var vehicle_owner_name : String? = null
)