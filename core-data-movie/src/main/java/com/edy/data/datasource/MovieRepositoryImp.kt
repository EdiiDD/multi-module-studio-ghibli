package com.edy.data.datasource

import com.edy.core_domain_movie.model.Movies
import com.edy.data.mapper.toMovie
import com.edy.core_domain.usecase.flow.Resource
import com.edy.core_domain.usecase.flow.resourceFailure
import com.edy.core_domain.usecase.flow.resourceSuccess
import com.edy.core_domain_movie.repository.MovieRepository
import com.edy.network.service.MovieClient
import com.edy.network.service.MovieServices
import javax.inject.Inject


class MovieRepositoryImp @Inject constructor(
    private val movieRemoteDataSource: MovieClient,
) : MovieRepository {

    companion object {
        private const val PAGE_SIZE = 20
        private const val PAGE_THRESHOLD = 3
    }

    override suspend fun getMovies(): Resource<Movies> {
        return when (val response = movieRemoteDataSource.getMovies()) {
            is Resource.Error -> resourceFailure(response.error)
            is Resource.Success -> response.data?.map { it.toMovie() }.run {
                resourceSuccess(Movies(movies = this))
            }
        }
    }
}