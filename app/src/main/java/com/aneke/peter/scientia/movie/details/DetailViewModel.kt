package com.aneke.peter.scientia.movie.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aneke.peter.scientia.models.Movie
import com.aneke.peter.scientia.movie.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    var id  = 0

    val movie = MutableLiveData<Movie>()

    val moviePoster = Transformations.map(movie){"https://image.tmdb.org/t/p/w500/${it.posterPath}"}
    val movieBackdrop = Transformations.map(movie){"https://image.tmdb.org/t/p/original/${it.backdropPath}"}
    val movieTitle = Transformations.map(movie){it.title}
    val movieDesc = Transformations.map(movie){it.overview}
    val isFavorite = Transformations.map(movie){it.isFavorite}
    val movieRating = Transformations.map(movie){"Rating: ${it.voteAverage}"}
    val movieReleaseDate = Transformations.map(movie){"Release Date ${it.releaseDate}"}

    fun fetchMovieDetails(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = movieRepository.fetchMovie(id)
            movie.postValue(response)
        }
    }

    fun updateFavorite(isFavorite : Boolean){
        val updatedMovie = movie.value!!.copy(isFavorite = isFavorite)

        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.updateMovie(updatedMovie)
        }
    }


}