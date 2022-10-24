package com.edy.core_domain.usecase.flow

import com.edy.core_error.model.ErrorEntity

sealed class Resource<T>(val data: T? = null, val error: ErrorEntity? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(data: T? = null, error: ErrorEntity?): Resource<T>(data, error)
}

fun <T> resourceFailure(error: ErrorEntity?) = Resource.Error<T>(null, error ?: ErrorEntity.Unknown)
fun <T> resourceSuccess(data: T?) = Resource.Success(data)
