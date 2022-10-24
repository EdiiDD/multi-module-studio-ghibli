package com.edy.studioghibli.di

import com.edy.feature_listmovies.di.ListMovieEntryModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ListMovieEntryModule::class
    ]
)

@InstallIn(SingletonComponent::class)
interface NavigationModule