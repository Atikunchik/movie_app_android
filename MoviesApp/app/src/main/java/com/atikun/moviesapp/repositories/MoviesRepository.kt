package com.atikun.moviesapp.repositories

import androidx.lifecycle.LiveData
import com.atikun.moviesapp.remote.ApiResponse
import com.atikun.moviesapp.utils.AppExecutors
import com.atikun.moviesapp.utils.NetworkBoundResource
import com.atikun.moviesapp.utils.Resource
import com.atikun.moviesapp.models.DataResponseMovies
import com.atikun.moviesapp.models.DataResponseYouTube
import com.atikun.moviesapp.models.Movie
import com.atikun.moviesapp.persistence.MovieDao
import com.atikun.moviesapp.remote.MovieInterface
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository@Inject constructor(private val movieInterface: MovieInterface, private val movieDao: MovieDao){


        fun getMovies(): LiveData<Resource<List<Movie>>> {

            return object : NetworkBoundResource<List<Movie>, DataResponseMovies>(AppExecutors) {
                override fun saveCallResult(item: DataResponseMovies) {
                    movieDao.deleteAllRows()
                    movieDao.insertRow(item.results)
                }

                override fun shouldFetch(data: List<Movie>): Boolean {
                    return true
                }

                override fun loadFromDb(): LiveData<List<Movie>> {
                    return movieDao.getAllRows()
                }

                override fun createCall(): LiveData<ApiResponse<DataResponseMovies>> {
                    return movieInterface.getMovies()
                }
            }.asLiveData()
        }

    suspend fun getYoutubeMovie(id:Int): Response<DataResponseYouTube> {
       return movieInterface.getMovie(id)
    }

}