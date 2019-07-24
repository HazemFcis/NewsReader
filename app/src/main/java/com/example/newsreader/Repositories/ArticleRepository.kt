package com.example.newsreader.Repositories

import android.arch.lifecycle.MutableLiveData
import com.example.newsreader.ApiService.ApiClient
import com.example.newsreader.Models.ArticleItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleRepository {

    fun getArticles(country:String?, category:String?, apiKey:String?):MutableLiveData<ArticleItem>
    {
       var data=MutableLiveData<ArticleItem>()
       var call: Call<ArticleItem> =ApiClient.getClient.getArticles(country, category, apiKey)
        call.enqueue(object : Callback<ArticleItem> {
            override fun onResponse(call: Call<ArticleItem>?, response: Response<ArticleItem>?) {
                 if (response?.body()?.status=="ok"){
                     data.value=response?.body()
                 }
            }
            override fun onFailure(call: Call<ArticleItem>?, t: Throwable?) {
            }
        })
        return data
    }

}