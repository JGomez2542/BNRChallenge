package com.bignerdranch.android.blognerdranch.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.blognerdranch.data.remote.BlogService
import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.models.PostMetadata
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val blogService: BlogService,
    private val metaDataLiveData: MutableLiveData<List<PostMetadata>>,
    private val postLiveData: MutableLiveData<Post>
) : Repository {

    override fun getPostMetadata() {
        GlobalScope.launch {
            val postMetadata = blogService.getPostMetadata()
            metaDataLiveData.postValue(postMetadata)
        }
    }

    override fun getPost(id: Int) {
        GlobalScope.launch {
            val post = blogService.getPost(id)
            postLiveData.postValue(post)
        }
    }

    override fun getMetaDataLiveData(): LiveData<List<PostMetadata>> = metaDataLiveData
    override fun getPostLiveData(): LiveData<Post> = postLiveData
}