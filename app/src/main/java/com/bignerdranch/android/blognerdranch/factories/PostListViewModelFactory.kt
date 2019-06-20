package com.bignerdranch.android.blognerdranch.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.ui.list.PostListViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class PostListViewModelFactory @Inject constructor(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PostListViewModel::class.java)) PostListViewModel(repository) as T
        else throw IllegalArgumentException("ViewModel not Found")
    }
}