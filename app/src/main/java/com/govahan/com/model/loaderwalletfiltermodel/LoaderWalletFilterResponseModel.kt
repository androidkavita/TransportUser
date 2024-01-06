package com.govahan.com.model.loaderwalletfiltermodel


data class LoaderWalletFilterResponseModel (

    var TotalAmount: String,
    var `data`: List<LoaderWalletFilterData>,
    var message: String,
    var status: Int
)

data class LoaderWalletFilterData(
    var amount: String,
    var credit: String,
    var debit: String,
    var name: String,
    var transaction_date: String,
    var user_id: Int,var create_at:String,var transaction_id:String,
)
