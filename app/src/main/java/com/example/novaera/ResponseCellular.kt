package com.example.novaera

import com.google.gson.annotations.SerializedName

data class ResponseCellular(@SerializedName("cellular") val list: List<DataClassCellular>)

data class ResponseBindCellular( @SerializedName("id") val id: Int,
                                 @SerializedName("name") val name: String,
                                 @SerializedName("price") val price: Int,
                                 @SerializedName("image") val image: String,
                                 @SerializedName("description") val description: String,
                                 @SerializedName("lastPrice") val lastPrice: Int,
                                 @SerializedName("credit") val credit: Boolean)