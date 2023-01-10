package com.sahibaliyev.mymobibook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Favorite")
data class BookModel(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo() val description: String,
    @ColumnInfo() val pdf: String
) : Serializable


//@Transient serilezable elemeden
//ctrl + alt + l