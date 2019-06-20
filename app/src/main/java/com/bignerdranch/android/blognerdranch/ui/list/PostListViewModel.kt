package com.bignerdranch.android.blognerdranch.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.blognerdranch.adapters.PostAdapter
import com.bignerdranch.android.blognerdranch.data.repository.Repository

class PostListViewModel(private val repository: Repository) : ViewModel() {

    init {
        repository.getPostMetadata()
    }

    fun getPostAdapter(): LiveData<PostAdapter> = Transformations.map(repository.getMetaDataLiveData()) { PostAdapter(it) }
}