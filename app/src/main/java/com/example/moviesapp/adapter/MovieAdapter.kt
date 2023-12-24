package com.example.moviesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.MovieItemBinding
import com.example.moviesapp.fragments.HomeFragmentDirections
import com.example.moviesapp.model.Movie


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(val itemBinding: MovieItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id
                && oldItem.movieTitle == newItem.movieTitle
                && oldItem.description == newItem.description
                && oldItem.genre == newItem.genre
                && oldItem.duration == newItem.duration
                && oldItem.rating == newItem.rating
                && oldItem.releasedDate == newItem.releasedDate
                && oldItem.trailerLink == newItem.trailerLink

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = differ.currentList[position]

        holder.itemBinding.movieImage.setImageResource(currentMovie.movieImage)
        holder.itemBinding.movieTitle.text = currentMovie.movieTitle
        holder.itemBinding.movieDetails.text = buildString {
            append(currentMovie.duration)
            append(" - ")
            append(currentMovie.genre)
        }
        holder.itemBinding.wishlistHomeButton.visibility =
            if (currentMovie.isOnWishlist)
            View.VISIBLE else View.GONE

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(currentMovie)

            it.findNavController().navigate(direction)
        }
    }


}