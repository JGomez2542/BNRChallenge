package com.bignerdranch.android.blognerdranch.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.ui.base.BaseViewModel
import kotlinx.coroutines.*


class PostViewModel(private val repository: Repository, private val _postLiveData: MutableLiveData<Post>) : BaseViewModel() {

    val postLiveData: LiveData<Post>
        get() = _postLiveData


    fun getPost(postId: Int) {
        launch {
            val post = repository.getPost(postId)
            _postLiveData.value = post
        }
    }
}