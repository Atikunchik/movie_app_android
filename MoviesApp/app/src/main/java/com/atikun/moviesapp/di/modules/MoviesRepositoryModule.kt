package com.atikun.moviesapp.di.modules

import com.atikun.moviesapp.persistence.MovieDao
import com.atikun.moviesapp.remote.MovieInterface
import com.atikun.moviesapp.repositories.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class MoviesRepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMoviesRepository(movieInterface: MovieInterface,movieDao: MovieDao): MoviesRepository {
        return MoviesRepository(movieInterface,movieDao)
    }

}