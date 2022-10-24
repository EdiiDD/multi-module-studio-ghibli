package com.edy.network.service

import com.edy.core_data_model.MovieDTO
import com.edy.core_domain.usecase.flow.Resource
import retrofit2.http.GET

interface MovieServices {

    @GET("films")
    suspend fun getFilms(): Resource<List<MovieDTO>>
}