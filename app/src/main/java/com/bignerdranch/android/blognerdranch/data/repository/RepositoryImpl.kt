package com.bignerdranch.android.blognerdranch.data.repository

import com.bignerdranch.android.blognerdranch.data.remote.BlogService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val blogService: BlogService) : Repository {

    override suspend fun getPostMetadata() = blogService.getPostMetadata()

    override suspend fun getPost(id: Int) = blogService.getPost(id)
}