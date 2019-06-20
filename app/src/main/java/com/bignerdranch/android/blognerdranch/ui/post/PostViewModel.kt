package com.bignerdranch.android.blognerdranch.ui.post

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.blognerdranch.data.repository.Repository

class PostViewModel(private val repository: Repository) : ViewModel() {

    val postLiveData
        get() = repository.getPostLiveData()

    fun getPost(postId: Int) {
        repository.getPost(postId)
    }
}