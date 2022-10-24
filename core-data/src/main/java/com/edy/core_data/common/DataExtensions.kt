package com.edy.core_data.common

/*
import com.dustsumit.data.models.ApiResponse
import com.dustsumit.data.models.HttpCodes
import com.dustsumit.domain.error.model.DomainError
import com.dustsumit.domain.exception.UnauthorizedException
import com.dustsumit.domain.usecase.flow.Either
import com.dustsumit.domain.usecase.flow.eitherFailure
import com.dustsumit.domain.usecase.flow.eitherSuccess
import retrofit2.Response

fun <T> Response<T>.mapToApiResponse() : ApiResponse<T> {
    return when(HttpCodes.fromCode(this.code())) {
        HttpCodes.OK -> {
            body()?.let {
                ApiResponse.Success(it)
            } ?: ApiResponse.NoDataResponse
        }
        HttpCodes.NOT_FOUND, HttpCodes.SERVER_ERROR,
        HttpCodes.BAD_REQUEST -> ApiResponse.BadRequest(this.errorBody()?.string())
        HttpCodes.FORBIDDEN,
        HttpCodes.UNAUTHORIZED -> ApiResponse.Unauthorized(this.errorBody()?.string())
        HttpCodes.TIME_OUT -> ApiResponse.Timeout
    }
}

fun <T> ApiResponse<T>.toDomain(): Either<T, DomainError> {
    return when (this) {
        is ApiResponse.Success -> eitherSuccess(this.data)
        is ApiResponse.ServerError,
        is ApiResponse.BadRequest -> eitherFailure(DomainError.ServerError)
        is ApiResponse.NoDataResponse -> eitherFailure(DomainError.NoDataError)
        is ApiResponse.Timeout -> eitherFailure(DomainError.TimeoutError)
        is ApiResponse.Unauthorized -> eitherFailure(DomainError.UnauthorizedError(this.errorBody))
        is ApiResponse.ApiError -> eitherFailure(DomainError.ApiError(this.error))
    }
}*/
