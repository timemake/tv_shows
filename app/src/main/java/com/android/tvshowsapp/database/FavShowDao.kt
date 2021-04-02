package com.android.tvshowsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favShow: FavShow)

    @Query("select * from favorite_table")
    fun getFavoriteShows(): LiveData<List<FavShow>>

    @Query("select count(*) from favorite_table where favorite_table.id = :id")
    suspend fun checkShow(id: String) : Int

    @Query("delete from favorite_table where favorite_table.id = :id")
    suspend fun removeFromFavorite(id: String): Int

    @Delete
    suspend fun delete(favShow: FavShow)

    @Query("delete from favorite_table")
    suspend fun deleteAll()
}