package com.bignerdranch.android.blognerdranch.di.application

import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.blognerdranch.data.remote.BlogService
import com.bignerdranch.android.blognerdranch.data.remote.RemoteServiceGenerator
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.data.repository.RepositoryImpl
import com.bignerdranch.android.blognerdranch.models.Post
import com.bignerdranch.android.blognerdranch.models.PostMetadata
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun providesBlogService() = RemoteServiceGenerator().createService(BlogService::class.java)

    @Provides
    @ApplicationScope
    fun providesRepository(
        blogService: BlogService,
        metaDataLiveData: MutableLiveData<List<PostMetadata>>,
        postLiveData: MutableLiveData<Post>
    ): Repository =
        RepositoryImpl(blogService = blogService, metaDataLiveData = metaDataLiveData, postLiveData = postLiveData)
}