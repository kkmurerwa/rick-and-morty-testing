package com.murerwa.rickandmortytesting.core.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.murerwa.rickandmortytesting.R

fun ImageView.loadImage(
    imageUrl: String,
    customDrawable: Int = R.drawable.ic_launcher_background
) {
    // Create spinner drawable for the glide placeholder
    val progressDrawable = CircularProgressDrawable(this.context)
    progressDrawable.strokeWidth = 5f
    progressDrawable.centerRadius = 30f
    progressDrawable.start()

    // Load images with glide
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(progressDrawable)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .error(customDrawable)
        .fallback(customDrawable)
        .into(this)
}