package com.bignerdranch.android.blognerdranch.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.blognerdranch.adapters.PostAdapter
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.ui.base.BaseViewModel
import kotlinx.coroutines.*

/**
 *
 */
class PostListViewModel(
    private val repository: Repository,
    private val _postAdapterLiveData: MutableLiveData<PostAdapter>
) : BaseViewModel() {

    val postAdapterLiveData: LiveData<PostAdapter>
        get() = _postAdapterLiveData

    fun getPostList() {
        launch {
            val postList = repository.getPostMetadata()
            _postAdapterLiveData.value = PostAdapter(postList)
        }
    }
}