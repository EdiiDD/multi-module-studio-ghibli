package com.edy.studioghibli.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NavigationModule::class]
)

interface AppComponent : AppProvider