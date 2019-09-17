package com.beetech.candycart.network

import okhttp3.Interceptor
import okhttp3.Response


class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val mainRequest = chain.request()
        val ongoing = mainRequest.newBuilder()
        var mainResponse = chain.proceed(ongoing.build())
        if (mainResponse.code() == 401) {
        }
        return mainResponse

    }
}