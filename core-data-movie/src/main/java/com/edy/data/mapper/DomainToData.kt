package com.edy.data.mapper

import com.edy.core_domain_movie.model.Movie
import com.edy.core_domain_movie.model.Movies
import com.edy.core_data_model.MovieDTO
import com.edy.core_data_model.MoviesDTO


fun MoviesDTO.toMovies() = Movies(
    movies = movies.map { it.toMovie() }
)

fun MovieDTO.toMovie() = Movie(
    title = title,
    releaseDate = releaseDate,
    picture = movieBanner,
    score = rtScore
)