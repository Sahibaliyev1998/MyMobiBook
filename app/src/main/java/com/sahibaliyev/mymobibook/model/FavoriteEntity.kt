package com.sahibaliyev.mymobibook.model


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
class FavoriteEntity () {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String?=null
    @ColumnInfo(name = "name")
    val name: String?=null
    @ColumnInfo(name = "image")
    val image: String?=null
    @ColumnInfo(name = "author")
    val author: String?=null


}