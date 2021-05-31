package com.brunets.newsapp.data.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.brunets.newsapp.data.NewsApi
import retrofit2.HttpException
import java.io.IOException

class ArticlePageSource(private val apiService: NewsApi) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: DEFAULT_PAGE_INDEX

            val response = apiService.getNews(
                company = "apple",
                pageSize = "10",
                page = page.toString()
            )

            LoadResult.Page(
                response.articles, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.articles.isNullOrEmpty() || (page * 10 >= DEV_LIMITED_REQUESTS)) null else page + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEV_LIMITED_REQUESTS = 100
    }
}