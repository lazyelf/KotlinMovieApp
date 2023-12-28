package com.example.moviesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.adapter.MovieAdapter
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.model.Movie
import com.example.moviesapp.viewmodel.MovieViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = (activity as MainActivity).movieViewModel
        (activity as MainActivity).setActionBarExpanded(MainActivity.ActionBarStyle.COLLAPSING)

        setUpRecyclerView()

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.sortMoviesByTitle -> {
                        sortMoviesByTitle()
                        true
                    }
                    R.id.sortMoviesByReleaseDate -> {
                        sortMoviesByReleaseDate()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setUpRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

        activity?.let {
            movieViewModel.getAllMovies().observe(
                viewLifecycleOwner
            ) { movies ->
                movieAdapter.differ.submitList(movies)
                updateUI(movies)
            }
        }
    }

    private fun updateUI(movies: List<Movie>){
        if (movies.isNotEmpty()){
            binding.noItems.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        } else {
            binding.noItems.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }

    private fun sortMoviesByTitle() {
        movieViewModel.getAllMoviesSortedByTitle().observe(
            this
        ) { list -> movieAdapter.differ.submitList(list) }
    }

    private fun sortMoviesByReleaseDate() {
        movieViewModel.getAllMoviesSortedByReleaseDate().observe(
            this
        ) { list -> movieAdapter.differ.submitList(list) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}