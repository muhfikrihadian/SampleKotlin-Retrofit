package com.muhfikrih.simplerestapi.models

import retrofit2.http.Body

data class CommentResponse(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,

)