package com.bignerdranch.android.blognerdranch.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.blognerdranch.adapters.PostAdapter
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PostListViewModel(
    private val repository: Repository,
    private val _postAdapterLiveData: MutableLiveData<PostAdapter>
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob
    val postAdapterLiveData: LiveData<PostAdapter>
        get() = _postAdapterLiveData
    private val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getPostList() {
        launch {
            val postList = repository.getPostMetadata()
            _postAdapterLiveData.value = PostAdapter(postList)
        }
    }
}