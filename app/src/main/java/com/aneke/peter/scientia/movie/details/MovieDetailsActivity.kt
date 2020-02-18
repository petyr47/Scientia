package com.aneke.peter.scientia.movie.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.aneke.peter.scientia.R
import com.aneke.peter.scientia.databinding.ActivityMovieDetailsBinding
import com.aneke.peter.scientia.fetchImage
import com.aneke.peter.scientia.models.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {

    private val detailViewModel : DetailViewModel by viewModel()

    private lateinit var binding : ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val id = intent.getIntExtra("movie_id", 0)
        detailViewModel.fetchMovieDetails(id)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        binding.lifecycleOwner = this
        binding.viewModel = detailViewModel

        supportActionBar?.hide()

        movie_detail_favorite_check_box.setOnClickListener {
            val isChecked = (it as CheckBox).isChecked
            detailViewModel.updateFavorite(isChecked)
        }




    }
}
