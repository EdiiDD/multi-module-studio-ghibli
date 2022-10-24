package com.edy.api

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.edy.core_navigation.di.AggregateFeatureEntry


abstract class ListMovieEntry : AggregateFeatureEntry {

    final override val featureRoute = "listmovies"

    final override val arguments = listOf(
        navArgument(ARG_MOVIE_ID) {
            type = NavType.StringType
            nullable = true
            defaultValue = null
        }
    )


    fun detailMovieDestination(movieId: String): String =
        "movie?$ARG_MOVIE_ID=$movieId"

    protected companion object {
        const val ARG_MOVIE_ID = "movieId"
    }
}