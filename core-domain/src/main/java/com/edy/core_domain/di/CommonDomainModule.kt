package com.edy.core_domain.di

import com.edy.core_domain.usecase.dispatchers.DefaultDispatcherProvider
import com.edy.core_domain.usecase.dispatchers.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonDomainModule {

    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(defaultDispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}
