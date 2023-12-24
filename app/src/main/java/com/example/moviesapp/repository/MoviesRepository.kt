package com.example.moviesapp.repository

import com.example.moviesapp.database.MoviesDatabase
import com.example.moviesapp.model.Movie

class MoviesRepository(private val db: MoviesDatabase) {

    suspend fun addMovie(movie: Movie) = db.getMoviesDAO().insertMovie(movie)

    suspend fun updateMovie(movie: Movie) = db.getMoviesDAO().updateMovie(movie)

    suspend fun deleteMovie(movie: Movie) = db.getMoviesDAO().deleteMovie(movie)

    fun getAllMovies() = db.getMoviesDAO().getAllMovies()

    fun getAllMoviesSortedByReleaseDate() = db.getMoviesDAO().getAllMoviesSortedByReleaseDate()

    fun getAllMoviesSortedByTitle()= db.getMoviesDAO().getAllMoviesSortedByTitle()

}