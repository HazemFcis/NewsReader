package com.example.newsreader.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.newsreader.Models.Article
import com.example.newsreader.R
import com.example.newsreader.Views.Details
import com.squareup.picasso.Picasso

class ArticleAdapter(private var dataList: ArrayList<Article>?, private val context: Context) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item_article,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = dataList!!.get(position)
        holder.bind(dataModel)
        holder.itemView.setOnClickListener {
            val intent = Intent(context,Details::class.java)
            intent.putExtra("imageUri",dataModel.urlToImage)
            intent.putExtra("author",dataModel.author)
            intent.putExtra("url",dataModel.url)
            intent.putExtra("publishedAt",dataModel.publishedAt)
            intent.putExtra("title",dataModel.title)
            intent.putExtra("description",dataModel.description)
            intent.putExtra("content",dataModel.content)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var thumbnail: ImageView? = null
        var titleTextView: TextView? = null
        var publishedAtTextView: TextView? = null
        init {
            thumbnail =itemLayoutView.findViewById(R.id.thumbnail)
            titleTextView = itemLayoutView.findViewById(R.id.article_title)
            publishedAtTextView = itemLayoutView.findViewById(R.id.article_publishedAt)
        }

        fun bind(article: Article) {
            Picasso
                .get()
                .load(article.urlToImage)
                .into(thumbnail)
            titleTextView?.text=article.title
            publishedAtTextView?.text=article.publishedAt!!.substring(0,10)
        }
    }
}