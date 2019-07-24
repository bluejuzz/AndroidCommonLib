package com.company.commonlibrary.retrofit


import android.text.TextUtils

import com.company.commonlibrary.BuildConfig

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2018/08/13 13:52
 * @des
 */
object RetrofitClient {
    private lateinit var baseUrl: String


    fun init(url: String) {
        baseUrl = url
    }

    /**
     * 连接超时时间
     */
    private const val TIME_OUT = 10L
    /**
     * 文件操作超时时间
     */
    private const val FILE_TIME_OUT = 60L

    /**
     * 文件下载拦截器
     */
    private val DOWNLOAD_INTERCEPTOR = DownloadInterceptor(object : DownloadListener {
        override fun onStartDownload() {
            downloadListener?.onStartDownload()
        }

        override fun onProgress(progress: Int) {
            downloadListener?.onProgress(progress)
        }

        override fun onFinishDownload() {
            downloadListener?.onFinishDownload()
        }

        override fun onFail(errorInfo: String) {
            downloadListener?.onFail(errorInfo)
        }
    })

    fun getRetrofit(): Retrofit {
        val retrofit: Retrofit by lazy {
            if (TextUtils.isEmpty(baseUrl)) {
                throw Throwable("You must first set baseUrl")
            }
            //  测试包打开日志
            val interceptor = HttpLoggingInterceptor()
            interceptor.level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            val builder = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)

            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create(GsonFactory.getGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .build()
        }
        return retrofit
    }

    private var downloadListener: DownloadListener? = null
    fun getFileRetrofit(downloadListener: DownloadListener?): Retrofit {
        val fileRetrofit: Retrofit by lazy {
            RetrofitClient.downloadListener = downloadListener
            val builder = OkHttpClient.Builder()
                    .addInterceptor(DOWNLOAD_INTERCEPTOR)
                    .connectTimeout(FILE_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(FILE_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(FILE_TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
            getRetrofit().newBuilder().client(builder.build()).build()
        }
        return fileRetrofit
    }
}

