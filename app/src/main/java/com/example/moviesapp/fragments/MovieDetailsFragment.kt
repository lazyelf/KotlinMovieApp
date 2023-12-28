package com.example.moviesapp.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.MainActivity
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMovieDetailsBinding
import com.example.moviesapp.model.Movie
import com.example.moviesapp.viewmodel.MovieViewModel
import java.time.format.DateTimeFormatter

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding : FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieViewModel: MovieViewModel

    private lateinit var currentMovie : Movie
    private val args : MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        (activity as MainActivity).setNavigationIcon()

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel = (activity as MainActivity).movieViewModel
        (activity as MainActivity).setActionBarExpanded(MainActivity.ActionBarStyle.REGULAR)

        currentMovie = args.movie!!

        binding.movieImage.setImageResource(currentMovie.movieImage)
        binding.movieTitle.text = currentMovie.movieTitle
        binding.movieRate.text = currentMovie.rating.toString()
        setWishlistButtonText()
        binding.movieDesc.text = currentMovie.description
        binding.genre.text = currentMovie.genre
        binding.releasedDate.text = currentMovie.releasedDate.format(DateTimeFormatter.ofPattern("yyyy, d, MMMM"))

        binding.watchTrailer.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(currentMovie.trailerLink))
            startActivity(browserIntent)
        }

        binding.wishlistButton.setOnClickListener {
            val newMovie = currentMovie.copy(isOnWishlist = !currentMovie.isOnWishlist)
            movieViewModel.updateMovie(newMovie)
            currentMovie = newMovie
            setWishlistButtonText()
        }
    }

    private fun setWishlistButtonText() {
        binding.wishlistButton.text = if (!currentMovie.isOnWishlist)
            getString(R.string.add_to_wishlist)
        else getString(R.string.remove_from_wishlist)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}