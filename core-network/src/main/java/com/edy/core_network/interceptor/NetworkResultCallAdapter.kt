package com.edy.core_network.interceptor

import com.edy.core_domain.usecase.flow.Resource
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter

class NetworkResultCallAdapter(
    private val resultType: Type
): CallAdapter<Type, Call<Resource<Type>>>
{
    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<Resource<Type>> {
        return NetworkResultCall(call)
    }
}
