package com.govahan.com.model.loaderwalletlistmodel

data class LoaderWalletListResponseModel(
    var TotalAmount: String,
    var `data`: List<LoaderWalletListData>,
    var message: String,
    var status: Int,var url: String,
)

data class LoaderWalletListData(
    var amount: String,
    var credit: String,
    var debit: String,
    var vendor_name: String,var transaction_type:String,var referal_type:Int,
    var transaction_date: String,var create_at:String,var transaction_id:String,
    var user_id: Int
)