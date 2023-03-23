/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations.data

import com.example.affirmations.R
import com.example.affirmations.model.Affirmation
import com.example.affirmations.model.AffirmationResources
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * [Datasource] generates a list of [AffirmationResources]
 */
class Datasource() {
    fun loadAffirmations(): List<AffirmationResources> {
        return listOf<AffirmationResources>(
            AffirmationResources(R.string.affirmation1, R.drawable.image1),
            AffirmationResources(R.string.affirmation2, R.drawable.image2),
            AffirmationResources(R.string.affirmation3, R.drawable.image3),
            AffirmationResources(R.string.affirmation4, R.drawable.image4),
            AffirmationResources(R.string.affirmation5, R.drawable.image5),
            AffirmationResources(R.string.affirmation6, R.drawable.image6),
            AffirmationResources(R.string.affirmation7, R.drawable.image7),
            AffirmationResources(R.string.affirmation8, R.drawable.image8),
            AffirmationResources(R.string.affirmation9, R.drawable.image9),
            AffirmationResources(R.string.affirmation10, R.drawable.image10)
        )
    }

    suspend fun loadAffirmation(): List<Affirmation> {
        return RetrofitHelper.getInstance().create(NewsApiService::class.java).loadNews().run {
            this.body()?.articles?.map {
                Affirmation(it.title ?: "", it.imageUrl ?: "")
            } ?: listOf()
        }
    }
}

interface NewsApiService {
    @GET("/v2/everything?q=tesla&from=2023-02-22&sortBy=publishedAt&apiKey=dfa28781f7eb46f8b33fdd642153b51c")
    suspend fun loadNews(): Response<NewsResponse>
}

object RetrofitHelper {

    val baseUrl = "https://newsapi.org/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Articles>
)

data class Articles(
    @SerializedName("urlToImage")
    val imageUrl: String,

    @SerializedName("title")
    val title: String

)

