package com.example.newsreader.Views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.newsreader.Adapters.ArticleAdapter
import com.example.newsreader.Models.Article
import com.example.newsreader.Models.ArticleItem
import com.example.newsreader.R
import com.example.newsreader.Room.ArticalEntity
import com.example.newsreader.Room.ArticleDataBase
import com.example.newsreader.ViewModels.ArticleViewModel
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker
import com.treebo.internetavailabilitychecker.InternetConnectivityListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), InternetConnectivityListener {

    private var articleViewModel:ArticleViewModel?=null
    var idCount:Int=1
    var articleEntityList:MutableList<ArticalEntity> = ArrayList()
    var articleList:MutableList<Article> = ArrayList()
    lateinit var db: ArticleDataBase
    var isUserNotConnected:Boolean=true
    private lateinit var mHandler: Handler
    private lateinit var mRunnable:Runnable
    lateinit var articalEntity:ArticalEntity
    lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        refresh()

        if (isUserNotConnected==true) {

            Thread {
                db.articleDoa().getAll().forEach({
                    article =
                        Article(it.author, it.title, it.description, it.url, it.urlToImage, it.publishedAt, it.content)
                    articleList.add(article)
                })
                recycler_view.adapter = ArticleAdapter(articleList as ArrayList<Article>, this!!)
            }.start()
        }
        else{
            setDataOnListAndDB()
    }
    }

    fun init(){

        mHandler = Handler()
        InternetAvailabilityChecker.init(this)
        var mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        mInternetAvailabilityChecker.addInternetConnectivityListener(this);
        db= Room.databaseBuilder(applicationContext,ArticleDataBase::class.java,"ArticleDB").build()
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {

        if (isConnected==false){
            isUserNotConnected = true
        }
        else  {
            isUserNotConnected = false
        }
    }

    fun refresh(){

        swipe_refresh_layout.setOnRefreshListener {
            mRunnable= Runnable {
                if (isUserNotConnected==true){
                    Toast.makeText(applicationContext,"No Internet", Toast.LENGTH_SHORT).show()
                }
                else {
                    setDataOnListAndDB()

                }
                swipe_refresh_layout.isRefreshing = false
            }
            mHandler.postDelayed(
                mRunnable,
                (2000).toLong()
            )
        }
    }

    fun setDataOnListAndDB(){

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)
        articleViewModel!!.setArticles()
        articleViewModel?.getArticles()?.observe(this, Observer { t: ArticleItem? ->

            recycler_view.adapter = ArticleAdapter(t?.articles, this!!)
            for (i in 0 until t?.articles?.size!!){
                articalEntity=ArticalEntity(idCount,t?.articles?.get(i)?.title,t?.articles?.get(i)?.description,t?.articles?.get(i)?.author,t?.articles?.get(i)?.url,t?.articles?.get(i)?.urlToImage,t?.articles?.get(i)?.publishedAt,t?.articles?.get(i)?.content)
                articleEntityList.add(articalEntity)
                idCount++
                Thread{
                    db.articleDoa().insertAll(articalEntity)
                }.start()
            } })
    }

}

