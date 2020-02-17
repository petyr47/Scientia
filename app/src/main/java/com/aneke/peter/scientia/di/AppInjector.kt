package com.aneke.peter.scientia.di

import com.aneke.peter.scientia.data.AppDatabase
import com.aneke.peter.scientia.data.PrefStore
import com.aneke.peter.scientia.movie.MovieRepository
import com.aneke.peter.scientia.movie.MovieViewModel
import com.aneke.peter.scientia.network.RetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { AppDatabase.getInstance(get()) }
    single { RetrofitClient.makeApiService() }
    single { PrefStore(get()) }

}

val viewModels = module {
    viewModel { MovieViewModel(get()) }
}

val repositories = module {
    single { MovieRepository(get(), get(), get()) }
}