package com.atikun.moviesapp.viewmodels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.atikun.moviesapp.utils.Resource
import com.atikun.moviesapp.models.Movie
import com.atikun.moviesapp.repositories.MoviesRepository



class MovieViewModel @ViewModelInject constructor(private val movieRepository: MoviesRepository): ViewModel() {


     fun getMovies(): LiveData<Resource<List<Movie>>> {
         return movieRepository.getMovies()
    }
}