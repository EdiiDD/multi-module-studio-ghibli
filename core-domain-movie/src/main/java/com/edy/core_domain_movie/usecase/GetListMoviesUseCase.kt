package com.edy.core_domain_movie.usecase

import com.edy.core_domain_movie.model.Movies
import com.edy.core_domain.usecase.dispatchers.DispatcherProvider
import com.edy.core_domain.usecase.flow.FlowUseCase
import com.edy.core_domain.usecase.flow.Resource
import com.edy.core_domain_movie.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetListMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    dispatcherProvider: DispatcherProvider,
) : FlowUseCase<String, Resource<Movies>>(dispatcherProvider) {

    override fun start(param: String): Flow<Resource<Movies>> = flow {
        emit(movieRepository.getMovies())
    }
}