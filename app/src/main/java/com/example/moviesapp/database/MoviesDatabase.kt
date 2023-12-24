package com.example.moviesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDAO() :MovieDAO

    companion object {
        @Volatile
        private var instance: MoviesDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            createDatabase(context).also {
                it.also { instance = it }
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                "movies_db"
            ).build()
    }
}