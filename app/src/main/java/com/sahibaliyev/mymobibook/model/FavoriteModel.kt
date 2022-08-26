package com.sahibaliyev.mymobibook.model


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
class FavoriteModel (

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String?,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "image") val image : String,
    @ColumnInfo(name = "author") val author : String


)