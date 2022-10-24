package com.edy.network.service

import javax.inject.Inject

class MovieClient @Inject constructor(
    private val services: MovieServices
) {
    suspend fun getMovies() = services.getFilms()
}