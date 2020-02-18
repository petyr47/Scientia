package com.aneke.peter.scientia.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.aneke.peter.scientia.models.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovies(movies: List<Movie>)

    @Query("DELETE FROM movie_table")
    suspend fun clearTable()

    @Query("SELECT * FROM movie_table")
    suspend fun fetchAllMovies(): List<Movie>

    @Query("SELECT * FROM movie_table")
    fun observeAllMovies() : LiveData<List<Movie>>

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query("SELECT * FROM movie_table WHERE id = :id")
    suspend fun fetchMovieForId(id: Int): List<Movie>

    @Query("SELECT * FROM movie_table WHERE id = :id")
    fun observeMovieForId(id: Int) : LiveData<List<Movie>>


    @Transaction
    suspend fun clearTableAndInsertNewMovies(movies: List<Movie>){
        clearTable()
        insertMovies(movies)
    }

}