package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.moviesapp.database.MoviesDatabase
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.repository.MoviesRepository
import com.example.moviesapp.viewmodel.MovieViewModel
import com.example.moviesapp.viewmodel.MoviesViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setUpViewModel() {
        val moviesRepository = MoviesRepository(MoviesDatabase(this))
        val viewModelProviderFactory = MoviesViewModelFactory(application, moviesRepository)
        ViewModelProvider(this, viewModelProviderFactory
            )[MovieViewModel::class.java].also { movieViewModel = it }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    enum class ActionBarStyle {
        REGULAR, COLLAPSING
    }

    fun setActionBarExpanded(actionBarStyle : ActionBarStyle) {
        when (actionBarStyle) {
            ActionBarStyle.REGULAR -> binding.appBarLayout.setExpanded(false, false)
            ActionBarStyle.COLLAPSING -> binding.appBarLayout.setExpanded(true, true)
        }
    }

}