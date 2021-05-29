package com.brunets.newsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.brunets.newsapp.data.NewsClient
import com.brunets.newsapp.data.model.Article
import com.brunets.newsapp.data.model.ArticlePageSource
import kotlinx.coroutines.flow.Flow

class NewsViewModel : ViewModel() {

    val articles = MutableLiveData<ArrayList<Article>>()

    val articlesFlow: Flow<PagingData<Article>> = Pager(PagingConfig(pageSize = 20)) {
        ArticlePageSource(NewsClient.service)
    }.flow
        .cachedIn(viewModelScope)


//    @SuppressLint("CheckResult")
//    fun getNews(page: String) {
//        NewsClient.service.getNews(company = "apple", pageSize = "10", page = page)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ new ->
//                articles.value?.let {
//                    val addedArticle: PagedList<Article>? = articles.value
//                    addedArticle?.addAll(new.articles)
//                    articles.value = addedArticle
//                } ?: run {
//                    articles.value = new.articles
//                }
//            }, { e ->
//                e.printStackTrace()
//            }, {
//                print("CABO")
//            }
//            )
//    }
}