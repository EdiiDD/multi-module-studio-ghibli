package com.edy.core_navigation.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component
interface NavigationComponent : NavigationProvider {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): NavigationComponent
    }
}