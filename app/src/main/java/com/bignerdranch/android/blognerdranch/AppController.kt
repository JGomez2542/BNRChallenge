package com.bignerdranch.android.blognerdranch

import android.app.Application
import com.bignerdranch.android.blognerdranch.di.application.ApplicationModule
import com.bignerdranch.android.blognerdranch.di.application.DaggerApplicationComponent

class AppController: Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule())
            .build()
    }
}