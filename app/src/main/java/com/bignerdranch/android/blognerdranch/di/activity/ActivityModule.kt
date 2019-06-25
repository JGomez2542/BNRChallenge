package com.bignerdranch.android.blognerdranch.di.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bignerdranch.android.blognerdranch.factories.PostListViewModelFactory
import com.bignerdranch.android.blognerdranch.factories.PostViewModelFactory
import com.bignerdranch.android.blognerdranch.ui.list.PostListViewModel
import com.bignerdranch.android.blognerdranch.ui.post.PostViewModel
import dagger.Module
import dagger.Provides

/**
 * Instantiates activity specific dependencies
 */
@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun providesPostListViewModel(postListViewModelFactory: PostListViewModelFactory) =
        ViewModelProviders.of(activity, postListViewModelFactory).get(PostListViewModel::class.java)

    @Provides
    @ActivityScope
    fun providesPostViewModel(postViewModelFactory: PostViewModelFactory) =
        ViewModelProviders.of(activity, postViewModelFactory).get(PostViewModel::class.java)

}