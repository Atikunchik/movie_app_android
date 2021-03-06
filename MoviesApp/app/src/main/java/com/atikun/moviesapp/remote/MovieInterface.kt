package com.atikun.moviesapp.remote


import androidx.lifecycle.LiveData
import com.atikun.moviesapp.models.DataResponseMovies
import com.atikun.moviesapp.models.DataResponseYouTube
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieInterface {

 @GET("discover/movie?sort_by=popularity.desc&api_key=b1f5db0adfa30b9e8714dba42e9edb44")
 fun getMovies(): LiveData<ApiResponse<DataResponseMovies>>



 @GET("movie/{id}/videos?api_key=18147b0826078c6d5e462bf97f3e032d")
 suspend fun getMovie(@Path("id") idMovie : Int): Response<DataResponseYouTube>

}