package com.govahan.com.activities.loadercompletedbookingdetails

data class UploadDocumentsResponse(
    val `data`: UploadDocumentsData,
    val message: String,
    val status: Int
)

data class UploadDocumentsData(
    val builty: String,
    val pod: String,
    val signature: String
)