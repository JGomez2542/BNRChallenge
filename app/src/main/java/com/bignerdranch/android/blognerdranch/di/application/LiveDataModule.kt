package com.bignerdranch.android.blognerdranch.di.application

import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.models.PostMetadata
import dagger.Module
import dagger.Provides

@Module
class LiveDataModule {

    @Provides
    fun providesPostMetaDataLiveData() = MutableLiveData<List<PostMetadata>>()

    @Provides
    fun providesPostLiveData() = MutableLiveData<Post>()
}