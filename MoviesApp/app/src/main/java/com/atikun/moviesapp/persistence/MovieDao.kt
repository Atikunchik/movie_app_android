package com.atikun.moviesapp.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.atikun.moviesapp.models.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllRows(): LiveData<List<Movie>>


    @Insert(onConflict = REPLACE)
    fun insertRow (movie:List<Movie>)

    @Query("DELETE FROM movies")
    fun deleteAllRows()

}