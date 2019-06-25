package com.bignerdranch.android.blognerdranch.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.models.Post
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class PostViewModel(private val repository: Repository, private val _postLiveData: MutableLiveData<Post>) : ViewModel(),
    CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob
    val postLiveData: LiveData<Post>
        get() = _postLiveData
    private val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getPost(postId: Int) {
        launch {
            val post = repository.getPost(postId)
            _postLiveData.value = post
        }
    }
}