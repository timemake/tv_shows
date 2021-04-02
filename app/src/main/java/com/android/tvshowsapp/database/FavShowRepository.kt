package com.android.tvshowsapp.database

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavShowRepository @Inject constructor(private val favShowDao: FavShowDao) {

    suspend fun insert(favShow: FavShow){
        favShowDao.insert(favShow)
    }

    suspend fun delete(favShow: FavShow){
        favShowDao.delete(favShow)
    }

    suspend fun deleteAll(){
        favShowDao.deleteAll()
    }

    fun getFavoriteShows(): LiveData<List<FavShow>> = favShowDao.getFavoriteShows()

    suspend fun checkShow(id: String) = favShowDao.checkShow(id)

    suspend fun removeFromFavorite(id: String){
        favShowDao.removeFromFavorite(id)
    }
}