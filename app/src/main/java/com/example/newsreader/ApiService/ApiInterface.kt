package com.example.newsreader.ApiService

import retrofit2.Call
import com.example.newsreader.Models.ArticleItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("top-headlines")
    fun getArticles(@Query ("country")country:String?, @Query ("category")category:String?, @Query("apiKey")apiKey:String?):
            Call<ArticleItem>
}