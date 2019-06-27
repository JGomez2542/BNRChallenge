package com.bignerdranch.android.blognerdranch.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.models.PostMetadata
import com.bignerdranch.android.blognerdranch.ui.base.BaseViewModel
import kotlinx.coroutines.*

/**
 * Updates PostListActivity with a list of posts
 */
class PostListViewModel(
    private val repository: Repository,
    private val _postAdapterLiveData: MutableLiveData<List<PostMetadata>>
) : BaseViewModel() {

    val postAdapterLiveData: LiveData<List<PostMetadata>> = _postAdapterLiveData

    /**
     * Gets a list of posts
     */
    fun getPostList() {
        launch {
            val postList = repository.getPostMetadata()
            _postAdapterLiveData.value = postList
        }
    }
}