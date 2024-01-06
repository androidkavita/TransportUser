package com.govahan.com.model.bookingpassengelmodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class BookingPassengerResponseModel (

    @SerializedName("status"  ) var status  : Int?               = null,
    @SerializedName("message" ) var message : String?            = null,
   // @SerializedName("booking" ) var bookingPassengerData : ArrayList<BookingPassengerData> = arrayListOf(),
    @SerializedName("data"    ) var bookingPassengerData    : BookingPassengerData?   = BookingPassengerData(),
    @SerializedName("user"    ) var bookingPassengerUser    : BookingPassengerUser?              = BookingPassengerUser(),
    @SerializedName("driver"  ) var bookingPassengerdriver  : BookingPassengerDriver?            = BookingPassengerDriver(),
    @SerializedName("ride_code" ) var bookingLoaderrideCode : String? = null

): Serializable

data class BookingPassengerData (


    @SerializedName("vehicle_id"     )              var vehicleId     :  Int?         = null,
    @SerializedName("driver_id"      )              var driverId      :  Int?         = null,
    @SerializedName("vehicle_name"   )              var vehicleName   :  String?      = null,
    @SerializedName("vehicle_no"     )              var vehicleNo     :  String?      = null,
    @SerializedName("available"      )              var available     :  Int?         = null,
    @SerializedName("vehicle_model"  )              var vehicleModel  :  String?      = null,
    @SerializedName("vehicle_type"   )              var vehicleType   :  String?      = null,
    @SerializedName("seat"           )              var seat          :  Int?         = null,
    @SerializedName("as vehicle_color" )   var      vehicleColor : String? = null,
    @SerializedName("as vehicle_wheel" )   var       vehicleWheel : String? = null,
    @SerializedName("from_address"   )              var fromAddress   :  String?      = null,
    @SerializedName("to_address"     )              var toAddress     :  String?      = null,
    @SerializedName("distance"       )              var distance      :  String?      = null,
    @SerializedName("fare"           )              var fare          :  String?      = null,
    @SerializedName("image"          )              var image         :  String?      = null,
    @SerializedName("booking_date"   )              var bookingDate   :  String?      = null,
    @SerializedName("booking_time"   )              var bookingTime   :  String?      = null,
    @SerializedName("booking_status" )              var bookingStatus :  String?      = null,
    @SerializedName("booking_create" )              var bookingCreate :  String?      = null,
    @SerializedName("booking_id"     )              var bookingId     :  String?      = null,
    @SerializedName("rating"         )              var rating        :  String?      = null

    //20oct
    /*@SerializedName("vehicle_id"     ) var vehicleId     : Int?    = null,
    @SerializedName("driver_id"      ) var driverId      : Int?    = null,
    @SerializedName("vehicle_name"   ) var vehicleName   : String? = null,
    @SerializedName("vehicle_no"     ) var vehicleNo     : String? = null,
    @SerializedName("available"      ) var available     : Int?    = null,
    @SerializedName("model"          ) var model         : String? = null,
    @SerializedName("v_type"         ) var vType         : String? = null,
    @SerializedName("seat"           ) var seat          : Int?    = null,
    @SerializedName("v_color"        ) var vColor        : String? = null,
    @SerializedName("wheel"          ) var wheel         : String? = null,
    @SerializedName("from_address"   ) var fromAddress   : String? = null,
    @SerializedName("to_address"     ) var toAddress     : String? = null,
    @SerializedName("distance"       ) var distance      : String? = null,
    @SerializedName("final_fare"     ) var finalFare     : String? = null,
    @SerializedName("image"          ) var image         : String? = null,
    @SerializedName("booking_date"   ) var bookingDate   : String? = null,
    @SerializedName("booking_time"   ) var bookingTime   : String? = null,
    @SerializedName("booking_status" ) var bookingStatus : String? = null,
    @SerializedName("booking_id"     ) var bookingId     : String? = null,
    @SerializedName("rating"         ) var rating        : String? = null,
    @SerializedName("booking_create"         ) var booking_create        : String? = null*/

): Serializable

data class BookingPassengerUser (

    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null

): Serializable

data class BookingPassengerDriver (

    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null

): Serializable