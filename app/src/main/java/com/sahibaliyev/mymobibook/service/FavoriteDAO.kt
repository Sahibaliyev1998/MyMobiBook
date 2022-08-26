package com.sahibaliyev.mymobibook.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sahibaliyev.mymobibook.model.FavoriteModel

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM favorite")
    fun getAll(): List<FavoriteModel>

    @Query("SELECT * FROM Favorite WHERE id IN (:favoriteIds)")
    fun loadAllByIds(favoriteIds: IntArray): List<FavoriteModel>

    @Query("SELECT * FROM favorite WHERE name LIKE :name AND " +
            "author LIKE :author LIMIT 1")
    fun findByName(name: String, author: String): FavoriteModel


    @Insert
    fun insertAll(vararg favorite: FavoriteModel)

    @Delete
    fun delete(favorite: FavoriteModel)
}