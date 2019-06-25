package com.bignerdranch.android.blognerdranch.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.ui.base.BaseViewModel
import kotlinx.coroutines.*

/**
 * Updates PostActivity with the details of a specific Post
 */
class PostViewModel(private val repository: Repository, private val _postLiveData: MutableLiveData<Post>) : BaseViewModel() {

    val postLiveData: LiveData<Post>
        get() = _postLiveData

    /**
     * Gets a post with the given [postId]
     */
    fun getPost(postId: Int) {
        launch {
            val post = repository.getPost(postId)
            _postLiveData.value = post
        }
    }
}