package com.company.commonlibrary.constant

import android.os.Environment

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/18 11:12
 * @des
 */
class CommonConstants {

    /**
     * Sp常量
     */
    object Sp {
        val SP_NAME = "sp_common"
    }

    /**
     * 文件常量
     */
    object FilePath {
        private val ROOT_PATH = Environment.getExternalStorageDirectory().absolutePath + "/aismono/wailaevaluation"
        /*** 缓存 */
        private val PATH_CACHE = "$ROOT_PATH/cache/"
        /*** 录音文件(公共) */
        val PATH_AUDIO = "$PATH_CACHE/audio/"
        /***  图片文件(公共) */
        val PATH_IMAGE = "$PATH_CACHE/image/"
        /***  视频文件(公共) */
        val PATH_VIDEO = "$PATH_CACHE/video/"
    }

    companion object {
        val BASE_URL = ""
    }
}
