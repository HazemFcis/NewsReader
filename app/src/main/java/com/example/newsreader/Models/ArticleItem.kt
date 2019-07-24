package com.example.newsreader.Models

class ArticleItem (status:String?, totalResults:Int?, articles: ArrayList<Article>?){

    var status:String?=null
    var totalResults:Int?=null
    var articles:ArrayList<Article>?=null

    init {
        this.status=status
        this.totalResults=totalResults
        this.articles=articles
    }
}