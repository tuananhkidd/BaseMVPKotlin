package com.beetech.candycart.data

import com.google.gson.annotations.SerializedName

class CategoryEntity(
        @SerializedName("id")
        var id: Int = 0,
        @SerializedName("name")
        var name: String = "",
        @SerializedName("thumbnail")
        var thumbnail: String = ""
)

