package com.edy.core_domain_movie.model

data class Movies(
    val movies: List<Movie>? = emptyList(),
)

data class Movie(
    val title: String,
    val releaseDate: String,
    val picture: String,
    val score: String
)