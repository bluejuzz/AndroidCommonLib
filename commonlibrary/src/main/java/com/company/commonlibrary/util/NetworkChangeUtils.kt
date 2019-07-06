package com.company.commonlibrary.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build

import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.Utils

/**
 * @author dlh
 * @email 18279727279@163.com
 * @date 2019/05/21 18:09
 * @des 网络变化监听工具类
 */
object NetworkChangeUtils {

    private var mChangeListener: NetworkChangeListener? = null
    private var mNetworkChangeReceiver: NetworkChangeReceiver? = null

    /**
     * 注册变化监听
     *
     * @param changeListener 回调
     */
    fun registerListener(changeListener: NetworkChangeListener) {
        mChangeListener = changeListener
        if (mChangeListener != null) {
            mChangeListener!!.callback(NetworkUtils.isConnected())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val connectivityManager =
                Utils.getApp().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // 请注意这里会有一个版本适配bug，所以请在这里添加非空判断
            connectivityManager.requestNetwork(
                NetworkRequest.Builder().build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        super.onAvailable(network)
                        if (mChangeListener != null) {
                            mChangeListener!!.callback(true)
                        }
                    }

                    override fun onLost(network: Network) {
                        super.onLost(network)
                        if (mChangeListener != null) {
                            mChangeListener!!.callback(false)
                        }
                    }
                })
        } else {
            val intentFilter = IntentFilter()
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
            mNetworkChangeReceiver = NetworkChangeReceiver()
            Utils.getApp().registerReceiver(mNetworkChangeReceiver, intentFilter)
        }
    }

    /**
     * 反注册网络变化广播
     */
    fun unRegisterListener() {
        if (mNetworkChangeReceiver != null) {
            Utils.getApp().unregisterReceiver(mNetworkChangeReceiver)
        }
    }

    /**
     * 网络变化回调
     */
    interface NetworkChangeListener {
        /**
         * 回调
         *
         * @param isConnected 是否连接
         */
        fun callback(isConnected: Boolean)
    }

    private class NetworkChangeReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo
                if (networkInfo != null && networkInfo.isAvailable) {
                    if (mChangeListener != null) {
                        mChangeListener!!.callback(true)
                    }
                } else {
                    if (mChangeListener != null) {
                        mChangeListener!!.callback(false)
                    }
                }
            }
        }
    }
}
