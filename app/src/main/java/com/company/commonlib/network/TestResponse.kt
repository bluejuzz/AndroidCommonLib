package com.company.commonlib.network

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/18 17:03
 * @des
 */
data class TestResponse<T>(
        val message: String? = null,
        val code: Int = 0,
        val body: T? = null,
        val url: String? = null
)
