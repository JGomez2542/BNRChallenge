package com.bignerdranch.android.blognerdranch.data.repository

import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.models.PostMetadata


interface Repository {
    suspend fun getPostMetadata(): List<PostMetadata>
    suspend fun getPost(id: Int): Post
}