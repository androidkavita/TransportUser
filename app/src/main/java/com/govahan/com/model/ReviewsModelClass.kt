package com.govahan.com.model

data class ReviewsModelClass(
    var `data`: ArrayList<ReviewsData>,
    var message: String,
    var status: Int
)

data class ReviewsData(
    var date: String,
    var rating: Int,
    var review: String,
    var user_name: String
)