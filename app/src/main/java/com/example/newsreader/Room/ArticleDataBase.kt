package com.example.newsreader.Room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(
    entities = [ArticalEntity::class], version = 4)
 abstract  class ArticleDataBase : RoomDatabase(){

  abstract fun articleDoa():ArticleDao

}