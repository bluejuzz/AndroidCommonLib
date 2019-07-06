package com.company.commonlibrary.bean

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/18 13:50
 * @des
 */
internal class ResponseExample2<T> : IResponse<T?> {
    override val message: String
        get() = errorMsg
    override val code: Int
        get() = errorCode
    override val body: T?
        get() = data
    override val isSuccessful: Boolean
        get() = errorCode == 0

    private var errorCode: Int = 0
    private var errorMsg: String = ""
    private var data: T? = null

}
