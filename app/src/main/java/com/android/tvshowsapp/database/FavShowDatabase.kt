package com.android.tvshowsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavShow::class], version = 1, exportSchema = false)
abstract class FavShowDatabase : RoomDatabase() {
    abstract fun favShowDao(): FavShowDao
}