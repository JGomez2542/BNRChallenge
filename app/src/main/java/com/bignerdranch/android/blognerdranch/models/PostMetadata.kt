package com.bignerdranch.android.blognerdranch.models

data class PostMetadata(
    val postId: Int? = null,
    val title: String? = null,
    val summary: String? = null,
    val author: Author? = null,
    val publishDate: String? = null
)