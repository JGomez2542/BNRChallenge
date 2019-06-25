package com.bignerdranch.android.blognerdranch.di.application

import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.blognerdranch.adapters.PostAdapter
import com.bignerdranch.android.blognerdranch.models.Post
import dagger.Module
import dagger.Provides

@Module
class LiveDataModule {

    @Provides
    fun providesPostAdapterLiveData() = MutableLiveData<PostAdapter>()

    @Provides
    fun providesPostLiveData() = MutableLiveData<Post>()
}