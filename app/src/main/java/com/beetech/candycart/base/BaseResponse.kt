package com.beetech.candycart.base

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("status")
    var status: Int = 0

    @SerializedName("msg")
    var message: String = ""
}