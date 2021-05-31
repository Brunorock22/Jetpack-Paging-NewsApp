package com.brunets.newsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.brunets.newsapp.data.NewsClient
import com.brunets.newsapp.data.model.Article
import com.brunets.newsapp.data.model.ArticlePageSource
import kotlinx.coroutines.flow.Flow

class NewsViewModel : ViewModel() {

    val articles = MutableLiveData<ArrayList<Article>>()

    val articlesFlow = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { ArticlePageSource(NewsClient.service)})
        .flow.cachedIn(viewModelScope)

}