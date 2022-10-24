package com.edy.feature_listmovies.ui

import androidx.compose.runtime.Immutable
import com.edy.core_domain_movie.model.Movie
import com.edy.core_error.model.ErrorEntity

data class ListMoviesUiState(
    val isLoading: Boolean = true,
    val isLoadingPagination: Boolean = false,
    val bestMovies: WrappedMovieStateList<MovieState> = WrappedMovieStateList(list = emptyList()),
    val favoritesMovies: WrappedMovieStateList<MovieState> = WrappedMovieStateList(list = emptyList()),
    val error: ErrorEntity? = null,
)

data class MovieState(
    val title: String,
    val releaseDate: String,
    val picture: String,
    val score: String
)

fun Movie.toState() = MovieState(
    title,
    releaseDate,
    picture,
    score
)

@Immutable
data class WrappedMovieStateList<T>(
    val list: List<T> = listOf()
)
