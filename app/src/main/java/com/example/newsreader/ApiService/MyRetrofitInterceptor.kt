package com.example.newsreader.ApiService

import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.io.IOException

class MyRetrofitInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {

        var request = chain!!.request()
        val accept = "application/json"
        val content = "application/json"
        val httpUrl =
            request.url().newBuilder().build()   //.addQueryParameter("lang",Shared.getDefaultLocale()).build()

        request = request.newBuilder()
            .addHeader("Accept", accept)
            .addHeader("Content-Type", content)
            .url(httpUrl)
            .build()
        val bodyMirror = bodyToString(request.body())
        return chain.proceed(request)
    }

    fun bodyToString(request: RequestBody?): String {
        try {
            val copy = request
            val buffer = Buffer()
            if (copy != null)
                copy.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }
    }
}