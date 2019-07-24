package com.example.newsreader.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.newsreader.Models.ArticleItem
import com.example.newsreader.Repositories.ArticleRepository

class ArticleViewModel:ViewModel() {
    var articleRepository =ArticleRepository()
    var articles=MutableLiveData<ArticleItem>()

    fun setArticles() {
        articles=articleRepository.getArticles("us","business","4c594968d6b5447f8fae78380fb087d2")
    }

    fun getArticles() : LiveData<ArticleItem> {
        return articles
    }

  }