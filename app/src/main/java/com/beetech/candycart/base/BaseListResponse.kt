package com.beetech.candycart.base

import com.google.gson.annotations.SerializedName

open class BaseListResponse<T> : BaseResponse() {

    @SerializedName("point")
    var point: Int = 0

    @SerializedName("current_page")
    var currentPage: Int = 0

    @SerializedName("total_page")
    var totalPage: Int = 0

    @SerializedName("total_item")
    var totalItem: Int = 0

    @SerializedName("data")
    var data: ArrayList<T> = ArrayList()
}