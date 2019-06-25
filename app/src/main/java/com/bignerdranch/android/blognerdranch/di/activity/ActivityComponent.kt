package com.bignerdranch.android.blognerdranch.di.activity

import com.bignerdranch.android.blognerdranch.factories.PostListViewModelFactory
import com.bignerdranch.android.blognerdranch.factories.PostViewModelFactory
import com.bignerdranch.android.blognerdranch.ui.list.PostListActivity
import com.bignerdranch.android.blognerdranch.ui.post.PostActivity
import dagger.Subcomponent

/**
 * Individual component for each activity
 */
@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(postListActivity: PostListActivity)

    fun inject(postActivity: PostActivity)

    fun getPostListViewModelFactory(): PostListViewModelFactory

    fun getPostViewModelFactory(): PostViewModelFactory
}