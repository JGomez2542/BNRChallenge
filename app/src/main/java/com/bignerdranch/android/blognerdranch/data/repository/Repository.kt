package com.bignerdranch.android.blognerdranch.data.repository

import androidx.lifecycle.LiveData
import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.models.PostMetadata

interface Repository {

    fun getPostMetadata()
    fun getPost(id: Int)
    fun getMetaDataLiveData(): LiveData<List<PostMetadata>>
    fun getPostLiveData(): LiveData<Post>
}