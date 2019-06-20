package com.bignerdranch.android.blognerdranch.data.remote

import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.models.PostMetadata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogService {

    @GET("/post-metadata")
    suspend fun getPostMetadata(): List<PostMetadata>

    @GET("/post/{id}")
    suspend fun getPost(@Path("id") id: Int): Post
}