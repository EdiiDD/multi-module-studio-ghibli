package com.edy.feature_listmovies.di

import com.edy.api.ListMoviesProvider
import com.edy.core_navigation.di.FeatureScoped
import dagger.Component

@FeatureScoped
@Component(
    modules = [ListMovieModule::class]
)
interface ProfileComponent : ListMoviesProvider