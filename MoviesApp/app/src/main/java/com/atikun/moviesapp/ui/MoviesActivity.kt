package com.atikun.moviesapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.atikun.moviesapp.R
import com.atikun.moviesapp.utils.Resource.Status
import com.atikun.moviesapp.adapters.MovieAdapter
import com.atikun.moviesapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler)


        initRecyclerView()


        movieViewModel.getMovies().observe(this, Observer {
            if (it.data != null) {
            when (it.status) {
                Status.ERROR -> {
                    movieAdapter.setList(it.data)
                }
                Status.SUCCESS -> {
                    movieAdapter.setList(it.data)
                }
            }
        } })


    }

    private fun initRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this@MoviesActivity, 2)
        movieAdapter = MovieAdapter(this)
        recyclerView.adapter = movieAdapter
    }
}
