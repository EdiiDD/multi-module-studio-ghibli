package com.edy.feature_listmovies.di

import com.edy.api.ListMovieEntry
import com.edy.core_navigation.di.FeatureEntry
import com.edy.core_navigation.di.FeatureEntryKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface ListMovieEntryModule {

    @Binds
    @Singleton
    @IntoMap
    @FeatureEntryKey(ListMovieEntry::class)
    fun profileEntry(entry: ListMovieEntryImpl): FeatureEntry
}