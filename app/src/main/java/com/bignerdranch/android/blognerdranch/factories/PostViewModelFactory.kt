package com.bignerdranch.android.blognerdranch.factories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.ui.post.PostViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * Factory for PostViewModel
 */
@Suppress("UNCHECKED_CAST")
class PostViewModelFactory @Inject constructor(
    private val repository: Repository,
    private val postMutableLiveData: MutableLiveData<Post>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) PostViewModel(
            repository,
            postMutableLiveData
        ) as T
        else throw IllegalArgumentException("ViewModel not Found")
}