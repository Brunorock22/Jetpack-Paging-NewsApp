package com.brunets.newsapp.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.brunets.newsapp.data.NewsApi
import java.lang.Exception

class ArticlePageSource(val apiService: NewsApi): PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: DEFAULT_PAGE_INDEX
            val response = apiService.getNews(
                company = "apple",
                pageSize = "1",
                page = page.toString()
            )

            LoadResult.Page(
                response.body()!!.articles, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.body()!!.articles.isEmpty()) null else page + 1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    companion object{
        const val  DEFAULT_PAGE_INDEX = 1
    }
}