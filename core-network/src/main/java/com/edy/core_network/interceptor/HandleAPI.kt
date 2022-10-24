package com.edy.core_network.interceptor

import com.edy.core_domain.usecase.flow.Resource
import com.edy.core_error.model.ErrorEntity
import com.squareup.moshi.JsonDataException
import java.io.FileNotFoundException
import java.io.IOException
import java.net.HttpURLConnection
import java.text.ParseException
import retrofit2.HttpException
import retrofit2.Response

fun <T : Any> handleApi(
    execute: () -> Response<T>,
): Resource<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            Resource.Success(body)
        } else {
            Resource.Error(error = ErrorEntity.Unknown)
        }
    } catch (e: Exception) {
        Resource.Error(error = e.getError())
    }
}