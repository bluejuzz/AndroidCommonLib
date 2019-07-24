package com.company.commonlib.network

import com.company.commonlibrary.bean.IResponse

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/18 17:03
 * @des
 */
data class AismonoResponse<T>(
        override val code: Int = 0,
        override val body: T? = null,
        val msg: String? = null,
        override val message: String? = msg,
        override val isSuccessful: Boolean = code == 8000
) : IResponse<T?>
