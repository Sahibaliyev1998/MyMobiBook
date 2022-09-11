package com.sahibaliyev.mymobibook.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sahibaliyev.mymobibook.model.FavoriteEntity
import com.sahibaliyev.mymobibook.service.FavoriteDAO
import io.reactivex.annotations.NonNull

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDAO


//Singleton

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /** fun getAppDatabase(context: Context): AppDatabase? {
        if (INSTANCE == null) {
        INSTANCE = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "favorite-database"
        )
        .allowMainThreadQueries()
        .build()
        }return INSTANCE
        }
        fun destroyInstance() {
        INSTANCE = null
        }*/

        private val lock = Any()
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(lock) {
            INSTANCE ?: getAppDatabase(context).also {
                INSTANCE = it
            }
        }

        fun getAppDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "bookfavoritedatabase"
        ).allowMainThreadQueries().build()

    }
}