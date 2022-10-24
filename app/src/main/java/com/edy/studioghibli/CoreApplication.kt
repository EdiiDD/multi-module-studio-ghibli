package com.edy.studioghibli;

import android.app.Application
import com.edy.studioghibli.di.AppProvider
import com.edy.studioghibli.di.DaggerAppComponent
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CoreApplication : Application() {
    lateinit var appProvider: AppProvider

    override fun onCreate() {
        super.onCreate()
        appProvider = DaggerAppComponent.builder().build()
    }
}

val Application.appProvider: AppProvider
    get() = (this as CoreApplication).appProvider