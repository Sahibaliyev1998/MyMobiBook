package com.sahibaliyev.mymobibook.util

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahibaliyev.mymobibook.model.FavoriteModel
import com.sahibaliyev.mymobibook.service.FavoriteDAO

@Database(entities = [FavoriteModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDAO

}