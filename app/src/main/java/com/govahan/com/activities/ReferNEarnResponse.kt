package com.govahan.com.activities

data class ReferNEarnResponse(
    val `data`: ReferNEarnData,
    val message: String,
    val status: Int
)

data class ReferNEarnData(
    val referal_code: String,
    val referal_link: String,
    val user_id: Int,
    val user_type: Int
)