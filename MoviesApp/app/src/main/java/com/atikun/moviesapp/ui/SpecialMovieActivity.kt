package com.atikun.moviesapp.ui

import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.atikun.moviesapp.R
import com.atikun.moviesapp.models.Movie
import com.atikun.moviesapp.repositories.MoviesRepository
import com.atikun.moviesapp.utils.Constants.Companion.API
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject


@AndroidEntryPoint
class SpecialMovieActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {

    private var id: Int = 0
    private lateinit var title: String
    private lateinit var vote: String
    private lateinit var overview: String
    private var key: String? = null
    private lateinit var txt: TextView
    private lateinit var title_movie: TextView
    private lateinit var rate: RatingBar
    private lateinit var player: YouTubePlayerFragment
    private lateinit var completableJob: CompletableJob

    @Inject
    lateinit var moviesRepository: MoviesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_special_movie)

        title_movie = findViewById(R.id.title_of_movie)
        txt = findViewById(R.id.txt)
        rate = findViewById(R.id.rate)
        player = (fragmentManager.findFragmentById(R.id.player) as YouTubePlayerFragment)

        checkIntent()

        handlingDataUsingCoroutine2()
    }


    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider,
        youTubePlayer: YouTubePlayer,
        wasRestored: Boolean
    ) {
        if (!wasRestored && key != null) {
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
            youTubePlayer.loadVideo(key)
            youTubePlayer.play()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider,
        youTubeInitializationResult: YouTubeInitializationResult
    ) {
        Toast.makeText(
            this@SpecialMovieActivity,
            youTubeInitializationResult.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }


    private suspend fun getkey(id: Int) {
        val response = moviesRepository.getYoutubeMovie(id)
        if (response.isSuccessful) {
            for (str in response.body()!!.results) {
                key = str.key
            }
        }
    }

    private fun checkIntent() {
        if (intent.getSerializableExtra("arr") != null) {
            val movie: Movie = intent.getSerializableExtra("arr") as Movie
            id = movie.id
            title = movie.title
            vote = movie.vote_average
            overview = movie.overview
            title_movie.text = title

            txt.text = overview
            rate.rating = 5 * vote.toFloat() / 10
        }
    }

    private fun handlingDataUsingCoroutine2() {
        val handler = CoroutineExceptionHandler { _, _ ->
        }

        completableJob = Job()
        completableJob.let { theJob ->
            CoroutineScope(IO + theJob).launch(handler) {
                getkey(id)
                withContext(Main) {
                    player.initialize(API, this@SpecialMovieActivity)
                    theJob.complete()
                }
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        completableJob.complete()
    }
}
