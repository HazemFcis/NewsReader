package com.example.newsreader.Models

import java.io.Serializable

class Article (  author:String?, title:String?, description:String?, url:String?, urlToImage:String?, publishedAt:String?, content:String?) {
    //var source: SourceModel?=null
    var author: String?=null
    var title: String?=null
    var description : String?=null
    var url : String?=null
    var urlToImage : String?=null
    var publishedAt : String?=null
    var content : String?=null

    init{
        //this.source = source
        this.author = author
        this.title = title
        this.description  = description
        this.url=url
        this.urlToImage=urlToImage
        this.publishedAt=publishedAt
        this.content=content
    }
}