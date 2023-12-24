package com.example.moviesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.moviesapp.model.Movie

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM Movies ORDER BY id")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM Movies ORDER BY releasedDate")
    fun getAllMoviesSortedByReleaseDate() : LiveData<List<Movie>>

    @Query("SELECT * FROM Movies ORDER BY movieTitle")
    fun getAllMoviesSortedByTitle() : LiveData<List<Movie>>
}