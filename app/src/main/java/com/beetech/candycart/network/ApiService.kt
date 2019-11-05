package com.beetech.candycart.network

import com.beetech.candycart.base.BaseListResponse
import com.beetech.candycart.data.CategoryEntity
import com.beetech.candycart.data.ProductResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-Type: application/json", "lang: vi")
    @GET("http://handycart.dovanbao.com/api/v1/product_category?category_id=-1&limit=20")
    fun getListCategories(@Query("page") page: Int)
            : Single<BaseListResponse<ProductResponse>>
}