package com.aneke.peter.scientia.movie

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.aneke.peter.scientia.R
import com.aneke.peter.scientia.fetchImage
import com.aneke.peter.scientia.models.Movie
import kotlinx.android.synthetic.main.layout_movie_item.view.*

class MovieAdapter (private val context: Context?) : RecyclerView.Adapter<MovieAdapter.MovieHolder>(){

    var list : List<Movie> = listOf()

    lateinit var onFavoriteButtonClicked : (Movie, Boolean) -> Unit
    lateinit var onMovieClicked : (Movie) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder =
        MovieHolder(LayoutInflater.from(context).inflate(R.layout.layout_movie_item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: MovieHolder, position: Int) = holder.bindMovie(list[position])


    inner class MovieHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindMovie(movie : Movie){
            val url = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
            itemView.movie_item_image.fetchImage(url)
            itemView.movie_item_title.text = movie.title
            itemView.movie_item_rating.text = "Rating: ${movie.voteAverage}"
            itemView.movie_item_release_date.text = "Release Date: ${movie.releaseDate}"
            itemView.movie_item_favorite_check_box.isChecked = movie.isFavorite
            itemView.movie_item_favorite_check_box.setOnClickListener {
                val isChecked = (it as CheckBox).isChecked
                Log.e("Movie Adapter", "Is checked state for ${movie.title} is $isChecked")
                onFavoriteButtonClicked.invoke(movie, isChecked)
            }
            itemView.movie_item_frame.setOnClickListener {
                onMovieClicked.invoke(movie)
            }

        }

    }
}
