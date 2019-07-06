package com.company.commonlibrary.base

import com.blankj.utilcode.util.ToastUtils

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/05 13:48
 * @des
 */

interface IView {

    /**
     * 显示加载
     *
     * @param tips 加载提示
     */
    fun showLoading(tips: String)

    /**
     * 隐藏加载
     */
    fun hideLoading()

    /**
     * 显示提示信息，默认吐司提示
     *
     * @param msg 提示信息
     */
    fun showMessage(msg: String) {
        ToastUtils.showShort(msg)
    }

}
