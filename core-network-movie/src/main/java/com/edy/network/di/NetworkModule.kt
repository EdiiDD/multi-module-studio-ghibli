package com.edy.network.di

import com.edy.core_network.interceptor.NetworkResultCallAdapterFactory
import com.edy.network.constants.SERVICE_URL_BASE
import com.edy.network.service.MovieClient
import com.edy.network.service.MovieServices
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("addHeadersInterceptor")
    @Singleton
    fun provideAddHeadersInterceptor(): Interceptor =
        Interceptor { chain ->
            val newRequest = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .apply {

                }
                .build()

            chain.proceed(newRequest)
        }

    @Provides
    @Named("httpLoggingInterceptor")
    @Singleton
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Named("publicHttpClient")
    @Singleton
    fun providePublicHttpClient(
        @Named("addHeadersInterceptor") addHeadersInterceptor: Interceptor,
        @Named("httpLoggingInterceptor") logInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(addHeadersInterceptor)
            .addInterceptor(logInterceptor)
            .build()

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    internal fun provideUrl(): HttpUrl = SERVICE_URL_BASE.toHttpUrl()

    @Provides
    @Singleton
    fun provideRetrofit(
        moshi: Moshi,
        @Named("publicHttpClient") okHttpClient: OkHttpClient,
        url: HttpUrl,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit)
            : MovieServices = retrofit.create(MovieServices::class.java)

    @Provides
    @Singleton
    fun provideMovieClient(movieServices: MovieServices): MovieClient {
        return MovieClient(movieServices)
    }
}



