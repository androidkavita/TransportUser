package com.govahan.com.activities

data class TransactionReportResponse(
    val `data`: ArrayList<TransactionReportData>,
    val message: String,
    val status: Int
)

data class TransactionReportData(
    val invoice_no: String,
    val transaction_date: Any,
    val transaction_id: String,
    val wallet_amount: String,val booking_amount:String
)