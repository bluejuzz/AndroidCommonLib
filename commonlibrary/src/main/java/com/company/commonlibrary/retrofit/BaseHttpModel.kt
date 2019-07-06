package com.company.commonlibrary.retrofit


import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.company.commonlibrary.BuildConfig
import com.google.gson.Gson
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.ObservableSubscribeProxy

import java.io.File
import java.net.ConnectException
import java.net.URLConnection

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.InputStream


/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2018/08/13 13:52
 * @des
 */
class BaseHttpModel private constructor() {

    /**
     * 文件上传使用
     *
     * @param url              请求地址
     * @param paramsBody       参数,可使用[.getRequestBody]获取
     * @param file             文件
     * @param disposeConverter 生命周期控制
     * @param callback         回调
     * @param <T>              返回数据类型
    </T> */
    fun <T> post(url: String, paramsBody: RequestBody, file: File, disposeConverter: AutoDisposeConverter<Response<ResponseBody>>, callback: BaseCallback<T>?) {
        when {
            !NetworkUtils.isConnected() -> callback?.run {
                onFailed(CommonException(-1, ConnectException()))
                onFinish()
            }
            else -> {
                val requestBody = file.asRequestBody(judgeType(file.name))
                val part = MultipartBody.Part.createFormData("file", file.name, requestBody)
                RetrofitClient.getFileRetrofit(null)
                        .create<RetrofitService>(RetrofitService::class.java)
                        .postFile(url, paramsBody, part)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .`as`<ObservableSubscribeProxy<Response<ResponseBody>>>(disposeConverter)
                        .subscribe(ResponseObserver(callback))
            }
        }
    }

    /**
     * 普通POST请求使用
     *
     * @param url              请求地址
     * @param requestBody      请求体,可使用[.getRequestBody]获取
     * @param disposeConverter 生命周期控制
     * @param callback         回调
     * @param <T>              返回数据基类
    </T> */
    fun <T> post(url: String, requestBody: RequestBody, disposeConverter: AutoDisposeConverter<Response<ResponseBody>>, callback: BaseCallback<T>?) {
        //判断是否有网络连接，无网络连接直接返回失败
        when {
            !NetworkUtils.isConnected() -> callback?.apply {
                onFailed(CommonException(-1, ConnectException()))
                onFinish()
            }
            else -> RetrofitClient.getRetrofit()
                    .create<RetrofitService>(RetrofitService::class.java)
                    .postJson(url, requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .`as`<ObservableSubscribeProxy<Response<ResponseBody>>>(disposeConverter)
                    .subscribe(ResponseObserver(callback))
        }

    }

    /**
     * 普通GET请求使用
     *
     * @param url              请求地址
     * @param params           参数
     * @param disposeConverter 生命周期控制
     * @param callback         回调
     * @param <T>              返回数据基类
    </T> */
    operator fun <T> get(url: String, params: Map<String, Any>?, disposeConverter: AutoDisposeConverter<Response<ResponseBody>>, callback: BaseCallback<T>?) {
        //判断是否有网络连接，无网络连接直接返回失败
        when (NetworkUtils.isConnected()) {
            false -> callback?.apply {
                onFailed(CommonException(-1, ConnectException()))
                onFinish()
            }
            true -> {

                params?.let {
                    RetrofitClient.getRetrofit()
                            .create<RetrofitService>(RetrofitService::class.java)
                            .getJson(url, it)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .`as`<ObservableSubscribeProxy<Response<ResponseBody>>>(disposeConverter)
                            .subscribe(ResponseObserver(callback))
                }

                params ?: let {
                    RetrofitClient.getRetrofit()
                            .create<RetrofitService>(RetrofitService::class.java)
                            .getJson(url)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .`as`<ObservableSubscribeProxy<Response<ResponseBody>>>(disposeConverter)
                            .subscribe(ResponseObserver(callback))
                }
            }
        }
    }


    /**
     * 文件下载
     *
     * @param downloadUrl      下载地址
     * @param saveFilepath     保存文件地址
     * @param downloadListener 下载回调
     */
    fun download(downloadUrl: String, saveFilepath: String, downloadListener: DownloadListener?) {
        when {
            !NetworkUtils.isConnected() -> downloadListener?.onFail("网络连接失败，请检查您的网络！")
            else -> {
                downloadListener?.apply {
                    onStartDownload()
                    RetrofitClient.getFileRetrofit(this)
                            .create<RetrofitService>(RetrofitService::class.java)
                            .downloadFile(downloadUrl)
                            .map<InputStream> { t -> t.byteStream() }
                            .observeOn(Schedulers.computation())
                            .map { inputStream -> FileIOUtils.writeFileFromIS(saveFilepath, inputStream) }
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .safeSubscribe(object : Observer<Boolean> {
                                override fun onSubscribe(d: Disposable) {
                                }

                                override fun onNext(isSuccessful: Boolean) = if (isSuccessful) {
                                    onFinishDownload()
                                } else {
                                    onFail("save file error")
                                }

                                override fun onError(e: Throwable) {
                                    e.message?.let { onFail(it) }
                                }

                                override fun onComplete() {

                                }
                            })
                }

            }
        }
    }

    /**
     * 获取请求体
     *
     * @param body 请求体
     * @param <B>  泛型
     * @return 请求体
    </B> */
    fun <B> getRequestBody(body: B): RequestBody {
        val content = Gson().toJson(body)
        val requestBody = content.toRequestBody(TYPE_JSON)
        if (BuildConfig.DEBUG) {
            LogUtils.d(content)
        }
        return requestBody
    }

    /**
     * 根据文件名来获取类型
     *
     * @param path 文件路径
     * @return 类型
     */
    private fun judgeType(path: String): MediaType? {
        val nameMap = URLConnection.getFileNameMap()
        val contentTypeFor = nameMap.getContentTypeFor(path)
        return if (contentTypeFor == null) TYPE_FILE else contentTypeFor.toMediaTypeOrNull()
    }

    companion object {
        private val TYPE_FILE = "multipart/form-data".toMediaTypeOrNull()
        private val TYPE_JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

        private var sInstance: BaseHttpModel? = null

        val instance: BaseHttpModel
            @Synchronized get() {
                if (sInstance == null) {
                    sInstance = BaseHttpModel()
                }
                return sInstance as BaseHttpModel
            }
    }
}
