package com.atikun.moviesapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atikun.moviesapp.models.Movie

@Database(entities = [Movie::class], version = 18, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}