package com.muhfikrih.simplerestapi.models

data class PostResponse(
    val id: Int,
    val userId: Int,
    val title: String?,
    val body: String?
)