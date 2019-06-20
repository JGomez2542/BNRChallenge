package com.bignerdranch.android.blognerdranch.di.application

import com.bignerdranch.android.blognerdranch.di.activity.ActivityComponent
import com.bignerdranch.android.blognerdranch.di.activity.ActivityModule
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class, LiveDataModule::class])
interface ApplicationComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

}