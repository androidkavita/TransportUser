package com.govahan.com.model

 data class AuthorizedFranchiseDetailsApi(
    var `data`: AuthorizedFranchiseData,
    var message: String,
    var status: Int
)

data class AuthorizedFranchiseData(
    var address: String,
    var email: String,
    var id: Int,
    var mobile_number: String,
    var name: String,
    var post_image: List<PostImage>,
    var vehicle_count: Int
)

data class PostImage(
    var image: String,
    var vehicle_name: String
)