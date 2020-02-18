package com.aneke.peter.scientia.movie

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.aneke.peter.scientia.data.AppDatabase
import com.aneke.peter.scientia.data.PrefStore
import com.aneke.peter.scientia.models.Movie
import com.aneke.peter.scientia.network.ApiInterface
import java.util.*

class MovieRepository (private val prefStore: PrefStore, private val db : AppDatabase, private val service : ApiInterface) {

    private val dao = db.dao()

    suspend fun refreshData(){
        if (prefStore.shouldRefreshData()){
            try {
                val response =  service.getTopRatedMovies(prefStore.apiKey)
                dao.insertMovies(response.movies)
                prefStore.lateSyncTime = Calendar.getInstance().timeInMillis
            }catch (e: Exception ){
                e.printStackTrace()
                if (e is SQLiteConstraintException) prefStore.lateSyncTime = Calendar.getInstance().timeInMillis
            }
        }
    }

    suspend fun updateMovie(movie : Movie){
        try {
            dao.updateMovie(movie)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    suspend fun fetchMovie(id : Int) : Movie? =
        try {
            dao.fetchMovieForId(id).firstOrNull()
        }catch (e: Exception){
            e.printStackTrace()
            null
        }


    fun observeMovieForId(id: Int) = dao.observeMovieForId(id)


    fun observeMovies() = dao.observeAllMovies()


}