package com.company.commonlibrary.retrofit

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2018/08/13 13:52
 * @des
 */
class DownloadInterceptor(private val downloadListener: DownloadListener) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        return response.newBuilder().body(
                response.body?.let { DownloadResponseBody(it, downloadListener) }).build()
    }
}
