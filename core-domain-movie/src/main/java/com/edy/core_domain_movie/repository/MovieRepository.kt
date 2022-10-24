package com.edy.core_domain_movie.repository

import com.edy.core_domain_movie.model.Movies
import com.edy.core_domain.usecase.flow.Resource

interface MovieRepository {
    suspend fun getMovies(): Resource<Movies>
}