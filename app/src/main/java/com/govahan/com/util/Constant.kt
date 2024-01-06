package com.govahan.com.util
interface Constant {
    companion object {

        var check: Int = 0

        //Local server url
        const val BASE_URL = "http://182.76.237.238/~jeevan/sila/api/"
        const val BASE_URL_2 = "https://www.sila.org.in/silainfo_api/Api/check_mobile"
        const val BASE_URL_3 = "https://www.sila.org.in/silainfo_api/Api/check_user"

        val IS_NOTIFICATION = "is_notification"
        var cartcount:Int? = 0
        var isItem:Boolean? = false
        var cartAmount:String? = ""
        var cartCurrency:String? = ""
        const val MY_PREFERENCES = "prefs"

    }
}