package com.atikun.moviesapp.models

import com.google.gson.annotations.SerializedName

data class DataResponseMovies(

    @SerializedName("results")
    val results : List<Movie>

)
