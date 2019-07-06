package com.company.commonlibrary.retrofit

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2018/08/13 13:51
 * @des
 */
interface DownloadListener {
    /**
     * 开始下载
     */
    fun onStartDownload()

    /**
     * 下载中
     *
     * @param progress 进度
     */
    fun onProgress(progress: Int)

    /**
     * 下载完成
     */
    fun onFinishDownload()

    /**
     * 下载失败
     *
     * @param errorInfo 失败信息
     */
    fun onFail(errorInfo: String)
}
