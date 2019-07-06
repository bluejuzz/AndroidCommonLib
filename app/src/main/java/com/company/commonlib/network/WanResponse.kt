package com.company.commonlib.network

/**
 * @author dlh
 * @company {@see <a>http://www.aismono.com</a>}
 * @email 18279727279@163.com
 * @date 2019/05/18 13:50
 * @des
 */
data class WanResponse<T>(
    val errorCode: Int = 0,
    val errorMsg: String? = null,
    val data: T? = null
)

