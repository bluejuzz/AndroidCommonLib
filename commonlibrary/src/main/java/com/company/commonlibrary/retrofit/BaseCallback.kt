package com.company.commonlibrary.retrofit


import com.blankj.utilcode.util.ToastUtils
import com.google.gson.internal.`$Gson$Types`

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */
abstract class BaseCallback<T> {
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
     * @param response 返回基类
     */
    abstract fun onSuccess(response: T)

    /**
     * 请求失败 默认根据错误码进行提示
     *
     * @param e 异常信息
     */
    fun onFailed(e: CommonException) {
        ToastUtils.showShort(CommonException.getHttpExceptionMessage(e.code, e.throwable))
    }

    /**
     * 结束（请求成功失败都会回调）
     */
    abstract fun onFinish()

}
