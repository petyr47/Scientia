package com.aneke.peter.scientia.movie

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.aneke.peter.scientia.R
import com.aneke.peter.scientia.movie.details.MovieDetailsActivity
import kotlinx.android.synthetic.main.activity_movie_details.movie_item_image
import kotlinx.android.synthetic.main.fragment_fav_movie.*
import kotlinx.android.synthetic.main.layout_movie_item.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import android.util.Pair as UtilPair


class FavMovieFragment : Fragment() {

    private val movieViewModel : MovieViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_fav_movie, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = MovieAdapter(this.context)
        adapter.onFavoriteButtonClicked = {movie, isFavorite ->
            movieViewModel.updateMovie(movie, isFavorite)
        }
        adapter.onMovieClicked = {
            val activityOptionsCompat =
                ActivityOptions.makeSceneTransitionAnimation(
                    activity as Activity,
                    UtilPair.create(movie_item_image as View, "ImagePoster"),
                    UtilPair.create(movie_item_title as View, "MovieTitle")
                )
            val intent = Intent(activity, MovieDetailsActivity::class.java)
            intent.putExtra("movie_id", it.id)
            startActivity(intent, activityOptionsCompat.toBundle())
        }
        fav_movie_list.adapter = adapter

        movieViewModel.favoriteMovies.observe(activity as LifecycleOwner, Observer {
            toggleEmptyView(it.isNullOrEmpty())
            adapter.list = it
            adapter.notifyDataSetChanged()
        })
    }

    private fun toggleEmptyView(listIsEmpty : Boolean) =
        when(listIsEmpty){
            true -> empty_view.visibility = View.VISIBLE
            false -> empty_view.visibility = View.GONE
        }

}