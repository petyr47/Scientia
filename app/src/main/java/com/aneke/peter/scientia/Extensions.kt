package com.aneke.peter.scientia

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

fun ImageView.fetchImage(url : String?) =
    Picasso.get().load(url).error(R.drawable.ic_no_camera).into(this)

@BindingAdapter("app:loadImage")
fun loadImage(view: ImageView , url: String?) = view.fetchImage(url)
