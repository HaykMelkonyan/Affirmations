package com.example.affirmations.data

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Articles>
)

data class Articles(
    @SerializedName("urlToImage")
    val imageUrl: String?,

    @SerializedName("title")
    val title: String?

)
