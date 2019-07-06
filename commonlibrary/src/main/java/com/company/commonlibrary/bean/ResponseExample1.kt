package com.company.commonlibrary.bean

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/18 17:03
 * @des
 */
internal class ResponseExample1<T> : IResponse<T?> {
    override val message: String = ""
    override val code: Int = 0
    override val body: T? = null
    override val isSuccessful: Boolean
        get() = code == 8000
}
