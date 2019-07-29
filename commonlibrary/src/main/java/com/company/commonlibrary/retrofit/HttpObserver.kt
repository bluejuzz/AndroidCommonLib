package com.company.commonlibrary.retrofit

import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.company.commonlibrary.bean.IResponse
import com.company.commonlibrary.retrofit.*
import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/24
 * @des
 */
abstract class HttpObserver<T> : Observer<ApiResponse<Any>> {
    override fun onChanged(t: ApiResponse<Any>?) {
        when (t) {
            is ApiSuccessResponse -> t.let {
                try {
                    val type = getType(0)
                    val toJson = Gson().toJson(it.body)
                    val response = Gson().fromJson<T>(toJson, type)
                    if (response != null) {
                        onSuccess(response)
                        onFinish()
                    } else {
                        onFailed(CommonException(-1, Exception()))
                        onFinish()
                    }
                } catch (e: Exception) {
                    onFailed(CommonException(-1, e))
                    onFinish()
                }
            }
            is ApiEmptyResponse -> {
                onFailed(CommonException(0, Exception("")))
                onFinish()
            }
            is ApiErrorResponse -> t.let {
                onFailed(CommonException(code = it.code, throwable = it.error))
                onFinish()
            }
        }

    }

    /**
     * 根据位置获取泛型类的类型
     *
     * @param position 位置
     * @return 类型
     */
    internal fun getType(position: Int): Type? {
        return getSuperclassTypeParameter(javaClass, position)
    }

    /**
     * 获取类泛型的类型
     *
     * @param subclass 判定类
     * @param position 位置
     * @return 类型
     */
    private fun getSuperclassTypeParameter(subclass: Class<*>, position: Int): Type? {
        val superclass = subclass.genericSuperclass
        if (superclass is Class<*>) {
            throw RuntimeException("Missing type parameter.")
        }
        val parameterized = superclass as ParameterizedType
        return `$Gson$Types`.canonicalize((parameterized.actualTypeArguments)[position])
    }

    /**
     * 请求成功
     *
     * @param response 返回泛型类
     */
    abstract fun onSuccess(response: T)

    /**
     * 请求失败 默认根据错误码进行提示
     *
     * @param e 异常信息
     */
    open fun onFailed(e: CommonException) {
        ToastUtils.showShort(CommonException.getHttpExceptionMessage(e.code, e.throwable))
    }

    /**
     * 结束（请求成功失败都会回调）
     */
    abstract fun onFinish()
}