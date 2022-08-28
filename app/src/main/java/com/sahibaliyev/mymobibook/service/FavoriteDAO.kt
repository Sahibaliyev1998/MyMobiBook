package com.sahibaliyev.mymobibook.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sahibaliyev.mymobibook.model.FavoriteEntity

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM favorite")
    suspend fun getAll(): List<FavoriteEntity>

    @Query("SELECT * FROM Favorite WHERE id IN (:favoriteIds)")
    suspend fun loadAllByIds(favoriteIds: IntArray): List<FavoriteEntity>

    @Query("SELECT * FROM favorite WHERE name LIKE :name AND " +
                "author LIKE :author LIMIT 1")
    suspend fun findByName(name: String, author: String): FavoriteEntity


    @Insert
    suspend fun insertAll(vararg favorite: FavoriteEntity) : List<Long>

    @Delete
    suspend fun delete(favorite: FavoriteEntity)
}