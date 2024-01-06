package com.govahan.com.model.passengerlivetrackingmodel


data class PassengerLiveTrackingResponseModel(
val expected_deliver_date: ExpectedDeliverDate,
val `data`: Data,
val distance: String,
val message: String,
val status: Int
)

data class ExpectedDeliverDate(
    val date: String,
    val time: String
)

data class Data(
    val booking_details: List<PassengerLiveTrackingResponseData>
)

data class PassengerLiveTrackingResponseData(
    val body_type: String,
    val booking_date: String,
    val booking_status: String,
    val booking_time: Any,
    val capacity: String,
    val driver_id: Int,
    val driver_image: String,
    val driver_name: String,
    val drop_lat: String,
    val drop_location: String,
    val drop_long: String,
    val fare: String,
    val mobile: String,
    val payment_mode: String,
    val picup_lat: String,
    val picup_location: String,
    val picup_long: String,
    val user_id: Int,
    val vechicle_id: String,
    val vehicle_image: String,
    val vehicle_name: String,
    val vehicle_numbers: String

)