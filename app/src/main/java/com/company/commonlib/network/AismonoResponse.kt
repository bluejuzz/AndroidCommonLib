package com.company.commonlib.network

import com.company.commonlibrary.bean.IResponse

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/18 17:03
 * @des
 */
data class AismonoResponse<T>(
        val msg: String? = null,
        val code: Int = 0,
        val body: T? = null
)
