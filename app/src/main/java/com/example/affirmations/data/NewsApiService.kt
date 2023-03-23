package com.example.affirmations.data

import retrofit2.Response
import retrofit2.http.GET


interface NewsApiService {
    @GET("/v2/everything?q=tesla&sortBy=publishedAt&apiKey=dfa28781f7eb46f8b33fdd642153b51c")
    suspend fun loadNews(): Response<NewsResponse>
}
