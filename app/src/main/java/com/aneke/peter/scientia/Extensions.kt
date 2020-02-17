package com.aneke.peter.scientia

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.fetchImage(url : String) =
    Picasso.get().load(url).error(R.drawable.ic_no_camera).into(this)
