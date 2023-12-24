package com.example.moviesapp.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.R
import com.example.moviesapp.model.Movie
import com.example.moviesapp.repository.MoviesRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class MovieViewModel(
        app: Application,
        private val moviesRepository: MoviesRepository)
: AndroidViewModel(app) {
    fun addMovie(movie: Movie) =
        viewModelScope.launch {
            moviesRepository.addMovie(movie)
        }

    fun updateMovie(movie: Movie) =
        viewModelScope.launch {
            moviesRepository.updateMovie(movie)
        }

    fun deleteMovie(movie: Movie) =
        viewModelScope.launch {
            moviesRepository.deleteMovie(movie)
        }

    fun getAllMovies() =
            moviesRepository.getAllMovies()

    fun getAllMoviesSortedByReleaseDate() =
            moviesRepository.getAllMoviesSortedByReleaseDate()

    fun getAllMoviesSortedByTitle() =
            moviesRepository.getAllMoviesSortedByTitle()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPlugs() {
        val plugs = listOf<Movie>(
            Movie(0,
                R.drawable.tenet,
                "Tenet",
                "Armed with only one word, Tenet, and fighting for the survival of the " +
                        "entire world, a Protagonist journeys through a twilight world of " +
                        "international espionage on a mission that will unfold in something " +
                        "beyond real time.",
                7.8,
                "2h 30min",
                "Action, Sci-Fi",
                LocalDate.of(2020, 9, 3),
                "https://www.youtube.com/watch?v=LdOM0x0XDMo",
                false),
            Movie(1,
                R.drawable.spider_man,
                "Spider-Man: Into the Spider-Verse",
                "Teen Miles Morales becomes the Spider-Man of his universe, and must " +
                        "join with five spider-powered individuals from other dimensions to stop" +
                        " a threat for all realities.",
                8.4,
                "1h 57min",
                "Action, Animation, Adventure",
                LocalDate.of(2018, 12, 14),
                "https://www.youtube.com/watch?v=tg52up16eq0",
                false),
            Movie(2,
                R.drawable.knives_out,
                "Knives Out",
                "A detective investigates the death of a patriarch of an eccentric, " +
                        "combative family.",
                7.9,
                "2h 10min",
                "Comedy, Crime, Drama",
                LocalDate.of(2019, 11, 27),
                "https://www.youtube.com/watch?v=qGqiHJTsRkQ",
                false),
            Movie(3,
                R.drawable.guardians_of_the_galaxy,
                "Guardians of the Galaxy",
                "A group of intergalactic criminals must pull together to stop a " +
                        "fanatical warrior with plans to purge the universe.",
                8.0,
                "2h 1min",
                "Action, Adventure, Comedy",
                LocalDate.of(2014, 8, 1),
                "https://www.youtube.com/watch?v=d96cjJhvlMA",
                false),
            Movie(4,
                R.drawable.avengers,
                "Avengers: Age of Ultron",
                "When Tony Stark and Bruce Banner try to jump-start a dormant " +
                        "peacekeeping program called Ultron, things go horribly wrong and it's " +
                        "up to Earth's mightiest heroes to stop the villainous Ultron from " +
                        "enacting his terrible plan.",
                7.3,
                "2h 21min",
                "Action, Adventure, Sci-Fi",
                LocalDate.of(2015, 5, 1),
                "https://www.youtube.com/watch?v=tmeOjFno6Do",
                false),
        )

        for (movie in plugs)
            addMovie(movie)
    }

}