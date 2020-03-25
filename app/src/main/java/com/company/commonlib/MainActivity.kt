package com.company.commonlib

import android.annotation.SuppressLint
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.company.commonlib.camerax.CameraXBarcodeAnalyzerActivity
import com.company.commonlib.contacts.ContactsTestActivity
import com.company.commonlib.network.*
import com.company.commonlibrary.base.BaseActivity
import com.company.commonlibrary.bean.RequestEntity
import com.company.commonlibrary.constant.CommonConstants
import com.company.commonlibrary.retrofit.BaseHttpModel
import com.company.commonlibrary.retrofit.DownloadListener
import com.company.commonlibrary.retrofit.HttpObserver
import com.company.commonlibrary.util.NetworkChangeUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */
class MainActivity : BaseActivity<BaseHttpModel>(), NetworkChangeUtils.NetworkChangeListener {

    override val layoutId = R.layout.activity_main

    override fun initView() {
        NetworkChangeUtils.registerListener(this)
        test_http.setOnClickListener {
            NetworkActivity.starter(this)
        }
        test_camerax.setOnClickListener {
            CameraXBarcodeAnalyzerActivity.starter(this)
        }
        test_contacts.setOnClickListener {
            ContactsTestActivity.starter(this)
        }
    }

    override fun initData() {

    }

    override fun onDestroy() {
        NetworkChangeUtils.unRegisterListener()
        super.onDestroy()
    }

    override fun callback(isConnected: Boolean) {
        showMessage("网络变化，是否有网络连接：$isConnected")
    }

}

