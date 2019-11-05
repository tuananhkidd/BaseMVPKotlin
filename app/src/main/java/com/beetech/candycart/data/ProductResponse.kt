package com.beetech.candycart.data


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ProductResponse : Serializable {
    @SerializedName("content")
    var content: String? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("expiry_date")
    var expiryDate: String? = null
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("price")
    var price: Int? = null
    @SerializedName("slug")
    var slug: String? = null
    @SerializedName("status")
    var status: Int? = null
    @SerializedName("stock")
    var stock: Int? = null
    @SerializedName("thumbnail")
    var thumbnail: String? = null
    @SerializedName("updated_at")
    var updatedAt: String? = null
    @SerializedName("weight")
    var weight: Int? = null
    @SerializedName("flag_favorite")
    var flagFavorite: Int = 0
    @SerializedName("discount")
    val percent: Int? = null
    @SerializedName("regular_price")
    val regularPrice: String = ""
    @SerializedName("seller_price")
    val sellerPrice: String = ""
    var quantity_select: Int = 1
    @SerializedName("flag_stock")
    var flagSoldOut: Int = 1
    @SerializedName("flag_expiry")
    var flagExpiration: Int = 1
    @SerializedName("sale")
    var sale: Int?=null
}