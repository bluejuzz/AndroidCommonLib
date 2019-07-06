package com.company.commonlibrary.retrofit


import com.company.commonlibrary.bean.IResponse
import com.google.gson.Gson

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Response

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/06 14:16
 * @des
 */
class ResponseSubscriber<T> internal constructor(private val mBaseCallback: BaseCallback<T>?) : Subscriber<Response<ResponseBody>> {
    override fun onSubscribe(s: Subscription?) {
    }

    override fun onNext(response: Response<ResponseBody>) {
        mBaseCallback?.apply {
            when {
                response.isSuccessful ->
                    when {
                        response.body() != null -> {
                            val string = response.body()!!.string()
                            val type = getType(0)
                            val baseHttpResponse = Gson().fromJson<T>(string, type)

                            when (type) {
                                is IResponse<*> -> {//实现了返回数据接口
                                    val httpResponse = baseHttpResponse as IResponse<*>
                                    if (!httpResponse.isSuccessful) {
                                        onFailed(CommonException(httpResponse.code, Exception(httpResponse.message)))
                                        onFinish()
                                    } else {
                                        onSuccess(baseHttpResponse)
                                        onFinish()
                                    }
                                }
                                else -> //未实现返回数据接口 需要自己处理返回信息
                                    when (baseHttpResponse) {
                                        null -> {
                                            onFailed(CommonException(-1, Exception()))
                                            onFinish()
                                        }
                                        else -> {
                                            onSuccess(baseHttpResponse)
                                            onFinish()
                                        }
                                    }
                            }
                        }
                        else -> {
                            onFailed(CommonException(-1, Exception()))
                            onFinish()
                        }
                    }
                else -> {
                    onFailed(CommonException(response.code(), Exception(response.message())))
                    onFinish()
                }
            }
        }
    }

    override fun onError(e: Throwable) {
        mBaseCallback?.apply {
            onFailed(CommonException(-1, e))
            onFinish()
        }
    }

    override fun onComplete() {

    }
}
