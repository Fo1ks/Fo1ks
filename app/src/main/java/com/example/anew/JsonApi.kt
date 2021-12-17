package com.example.anew
import retrofit2.http.Body
import retrofit2.http.POST


interface JsonApi {

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post
}