package com.company.commonlib.network

import com.company.commonlibrary.bean.IResponse

/**
 * @author dlh
 * @company {@see <a>http://www.aismono.com</a>}
 * @email 18279727279@163.com
 * @date 2019/05/18 17:03
 * @des
 */
data class AismonoResponse<T> private constructor(
    override val message: String = "",
    override val code: Int = 0,
    override val body: T? = null
) : IResponse<T?> {
    override val isSuccessful: Boolean
        get () = code == 8000

}
