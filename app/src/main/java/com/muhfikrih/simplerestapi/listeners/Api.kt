package com.muhfikrih.simplerestapi.listeners

import com.muhfikrih.simplerestapi.models.CommentResponse
import com.muhfikrih.simplerestapi.models.CreatePostResponse
import com.muhfikrih.simplerestapi.models.PostResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("posts")
    fun getPosts(): Call<ArrayList<PostResponse>>

    @GET("posts")
    fun getPosts(
        @Query("userId") userId: Int,
        @Query("id") id: Int,
    ): Call<ArrayList<PostResponse>>

    @GET("posts")
    fun getPosts(
        @QueryMap parameters: HashMap<String, String>
    ): Call<ArrayList<PostResponse>>

    @GET("posts/{id}/comments")
    fun getComments(
        @Path("id") idPost: Int
    ): Call<ArrayList<CommentResponse>>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Call<CreatePostResponse>

    @FormUrlEncoded
    @PUT("posts/{id}")
    fun putPost(
        @Path("id") id: Int,
        @Field("id") idPost: Int,
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Call<PostResponse>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    fun patchPost(
        @Path("id") id: Int,
        @Field("id") idPost: Int,
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Call<PostResponse>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id:Int) : Call<Void>

}