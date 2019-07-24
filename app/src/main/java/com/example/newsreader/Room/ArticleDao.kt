package com.example.newsreader.Room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entity: ArticalEntity?)

    @Query("SELECT * FROM article_Info")
    fun getAll(): List<ArticalEntity>


}