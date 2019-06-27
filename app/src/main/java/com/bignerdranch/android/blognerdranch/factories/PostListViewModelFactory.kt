package com.bignerdranch.android.blognerdranch.factories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.models.PostMetadata
import com.bignerdranch.android.blognerdranch.ui.list.PostListViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * Factory for PostListViewModel
 */
@Suppress("UNCHECKED_CAST")
class PostListViewModelFactory @Inject constructor(
    private val repository: Repository, private val postListLiveData: MutableLiveData<List<PostMetadata>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) PostListViewModel(
            repository, postListLiveData
        ) as T
        else throw IllegalArgumentException("ViewModel not Found")
}