package com.muhfikrih.simplerestapi.models

data class CreatePostResponse(
    val id: Int,
    val userId: Int,
    val title: String?,
    val body: String?
)