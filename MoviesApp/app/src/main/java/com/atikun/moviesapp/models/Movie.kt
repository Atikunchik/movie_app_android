package com.atikun.moviesapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie (


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "iddatabase")
    var iddatabase: Int,

    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "title")
    val title : String,

    @ColumnInfo(name = "vote_average")
    val vote_average : String,

    @ColumnInfo(name = "overview")
    val overview : String ,

    @ColumnInfo(name = "poster_path")
    val poster_path : String) : Serializable{

}