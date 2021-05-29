package com.brunets.newsapp.data.model

import androidx.paging.PagedList

data class New(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)