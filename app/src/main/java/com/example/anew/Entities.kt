package com.example.anew

data class Post(
    val userId: Int,
    val title: String,
    val body: String,
    val id: Int? = null
)

