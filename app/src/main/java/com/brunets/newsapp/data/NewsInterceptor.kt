package com.brunets.newsapp.data

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class NewsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("apiKey", PUBLIC_KEY)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}