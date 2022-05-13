package com.atikun.moviesapp.di.modules

import android.content.Context
import androidx.room.Room
import com.atikun.moviesapp.persistence.MovieDao
import com.atikun.moviesapp.persistence.MovieDatabase
import com.atikun.moviesapp.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RoomModuleGlobal {

    @Singleton
    @Provides
    fun providePostDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room
            .databaseBuilder(context, MovieDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDAO(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

}