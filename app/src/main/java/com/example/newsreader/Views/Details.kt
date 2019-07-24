package com.example.newsreader.Views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.newsreader.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class Details : AppCompatActivity() {
    lateinit var stringTemp:String
    lateinit var _stringTemp:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setDetails()
        shareClick()
    }

    fun setDetails(){
        Picasso
            .get()
            .load(intent.getStringExtra("imageUri"))
            .into(photo)

        stringTemp= if (intent.getStringExtra("title")==null)  " " else  intent.getStringExtra("title")
        article_title.setText(stringTemp)

        stringTemp= if (intent.getStringExtra("publishedAt")==null)  " " else  intent.getStringExtra("publishedAt").substring(0,10)
        _stringTemp= if (intent.getStringExtra("author")==null)  " " else  intent.getStringExtra("author")
        article_byline.setText(stringTemp+ "  BY   " +_stringTemp)

        stringTemp=  if (intent.getStringExtra("description")==null)  " " else  intent.getStringExtra("description")
        _stringTemp= if (intent.getStringExtra("content")==null)  " " else  intent.getStringExtra("content").substringBefore("[")
        article_body.setText("DESCRIPTION : "+stringTemp+"\n \n "+"Content : "+_stringTemp)
    }

    fun shareClick(){
        share_fab.setOnClickListener {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL")
            i.putExtra(Intent.EXTRA_TEXT, intent.getStringExtra("url"))
            startActivity(Intent.createChooser(i, "Share URL"))
        }
    }
}
