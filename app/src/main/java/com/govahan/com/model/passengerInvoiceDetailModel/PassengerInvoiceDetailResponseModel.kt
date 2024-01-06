package com.govahan.com.model.passengerInvoiceDetailModel


data class PassengerInvoiceDetailResponseModel(
    var `data`: List<PassengerInvoiceDetailData>,
    var message: String,
    var ownerDetails: List<Any>,
    var status: Int,
    var userDetails: PassengerInvoiceDetailUserDetails
)

data class PassengerInvoiceDetailData(
    var assigned_driver_id: Any,
    var body_type: String,
    var booking_date: String,
    var booking_id: String,
    var booking_status: String,
    var booking_time: String,
    var cancelation_reason: String,
    var capacity: String,
    var created_at: String,
    var currency: String,
    var distance: String,
    var driver_id: String,
    var driver_name: PassengerInvoiceDetailDriverName,
    var drop_lat: String,
    var drop_location: String,
    var drop_long: String,
    var fare: String,
    var id: Int,
    var fuel_charge: String,
    var toll_tax: String,
    var driver_fee: String,
    var tax: String,
    var freight_amount: String,

    var invoice_number: String,
    var payment_mode: String,
    var payment_status: String,
    var picup_lat: String,
    var picup_location: String,
    var picup_long: String,
    var start_code: String,
    var start_date: String,
    var transaction_id: String,
    var updated_at: String,
    var user_id: String,
    var vechicle_id: String,
    var vehicle_name: String,
    var vehicle_no: String,
    var vehicle_numbers: String,
    var year_model: String
)

data class PassengerInvoiceDetailUserDetails(
    var email: String,
    var mobile_number: String,
    var name: String,
    var v_id: Any
)

data class PassengerInvoiceDetailDriverName(
    var driver_name: String,
    var mobile_number: String
)