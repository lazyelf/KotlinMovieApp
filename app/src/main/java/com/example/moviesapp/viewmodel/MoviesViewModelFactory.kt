package com.example.moviesapp.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.repository.MoviesRepository

class MoviesViewModelFactory(
    val app:Application,
    private val moviesRepository: MoviesRepository
    ) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.O)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        val viewModel = MovieViewModel(app, moviesRepository)
        if (viewModel.getAllMovies().value.isNullOrEmpty())
            viewModel.addPlugs()
        return viewModel as T
    }
}