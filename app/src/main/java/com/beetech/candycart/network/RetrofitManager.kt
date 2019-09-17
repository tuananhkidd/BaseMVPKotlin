package com.beetech.candycart.network

import com.beetech.candycart.BuildConfig
import com.beetech.candycart.CandyCartAppplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private val REQUEST_TIMEOUT: Long = 60000

    fun createRetrofit(baseUrl: String): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient
                .addInterceptor(logging)
        }


        httpClient.addInterceptor(TokenInterceptor())
            .addInterceptor(NetworkCheckerInterceptor(CandyCartAppplication.instance.getContext()))

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private var apiService = createRetrofit(BuildConfig.BASE_URL).create(ApiService::class.java)

    fun getApiService(): ApiService {
        if (apiService == null) {
            apiService = createRetrofit(BuildConfig.BASE_URL).create(ApiService::class.java)
        }
        return apiService
    }


    private fun createPostRequest(request: Any): RequestBody {
        val jsonRawString = Gson().toJson(request)
        return RequestBody.create(MultipartBody.FORM, jsonRawString ?: "")
    }


}