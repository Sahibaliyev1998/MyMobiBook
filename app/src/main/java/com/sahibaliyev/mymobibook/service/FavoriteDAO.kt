package com.sahibaliyev.mymobibook.service

import androidx.room.*
import com.sahibaliyev.mymobibook.model.BookModel

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM favorite")
    fun getAll(): List<BookModel>

    @Query("SELECT * FROM Favorite WHERE id IN (:favoriteIds)")
    suspend fun loadAllByIds(favoriteIds: IntArray): List<BookModel>

    @Query(
        "SELECT * FROM favorite WHERE name LIKE :name AND " +
                "author LIKE :author LIMIT 1"
    )
    suspend fun findByName(name: String, author: String): BookModel

    @Query("DELETE FROM favorite")
    suspend fun deleteAllFavorite()

//    @Insert(onConflict = OnConflictStrategy.REPLACE)

    @Insert
    suspend fun insertAll(vararg favorite: BookModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: BookModel)

    @Delete
    suspend fun delete(favorite: BookModel)
}