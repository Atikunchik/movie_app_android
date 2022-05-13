package com.atikun.moviesapp.models

import com.google.gson.annotations.SerializedName

data class DataResponseYouTube (

    @SerializedName("results")
    val results: List<YouTube>
)