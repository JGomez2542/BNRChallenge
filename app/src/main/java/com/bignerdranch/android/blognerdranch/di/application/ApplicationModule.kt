package com.bignerdranch.android.blognerdranch.di.application

import com.bignerdranch.android.blognerdranch.data.remote.BlogService
import com.bignerdranch.android.blognerdranch.data.remote.RemoteServiceGenerator
import com.bignerdranch.android.blognerdranch.data.repository.Repository
import com.bignerdranch.android.blognerdranch.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * Instantiates application wide dependencies
 */
@Module
class ApplicationModule {

    @Provides
    @ApplicationScope
    fun providesBlogService() = RemoteServiceGenerator().createService(BlogService::class.java)

    @Provides
    @ApplicationScope
    fun providesRepository(blogService: BlogService): Repository = RepositoryImpl(blogService = blogService)
}