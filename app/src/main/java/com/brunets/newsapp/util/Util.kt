package com.brunets.newsapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.brunets.newsapp.R
import com.bumptech.glide.Glide

fun ImageView.loadImg(url: String) {
    val options = RequestOptions()
        .placeholder(getProgressDrawable(context))
        .error(R.drawable.ic_news)
    Glide.with(context).setDefaultRequestOptions(options)
        .load(url).into(this)
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}