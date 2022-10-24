package com.edy.data.di

import com.edy.data.datasource.MovieRepositoryImp
import com.edy.core_domain_movie.repository.MovieRepository
import com.edy.network.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class MovieRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsMovieRepository(movieRepositoryImp: MovieRepositoryImp): MovieRepository
}