package com.aneke.peter.scientia.movie

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aneke.peter.scientia.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {


    val movies = repository.observeMovies()

    val favoriteMovies = Transformations.map(repository.observeMovies()){it.filter {movie ->  movie.isFavorite }}


    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.refreshData()
        }
    }

    fun updateMovie(movie: Movie, favorite: Boolean) {
        val updatedMovie = movie.copy(isFavorite = favorite)
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMovie(updatedMovie)
        }
    }

}