package com.brunets.newsapp

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.brunets.newsapp.data.NewsClient
import com.brunets.newsapp.data.model.Article
import com.brunets.newsapp.data.model.ArticlePageSource

class NewsViewModel @ViewModelInject constructor(client: NewsClient) : ViewModel() {

    val articles = MutableLiveData<ArrayList<Article>>()

    val articlesFlow = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { ArticlePageSource(client.service) })
        .flow
        .cachedIn(viewModelScope)
}