package com.company.commonlib.network

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils

import com.company.commonlib.R
import com.company.commonlib.contacts.ContactsTestActivity
import com.company.commonlibrary.base.BaseActivity
import com.company.commonlibrary.bean.RequestEntity
import com.company.commonlibrary.constant.CommonConstants
import com.company.commonlibrary.retrofit.BaseHttpModel
import com.company.commonlibrary.retrofit.DownloadListener
import com.company.commonlibrary.retrofit.HttpObserver
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_network.*

class NetworkActivity : BaseActivity<BaseHttpModel>() {
    override val layoutId: Int
        get() = R.layout.activity_network

    @SuppressLint("AutoDispose")
    override fun initView() {

        http_post.setOnClickListener {
            showLoading("加载中")
            val requestBody = mViewModel.getRequestBody(RequestEntity<Map<String, Any>>(hashMapOf("phone" to 18279727279L)))
            mViewModel.post("https://http.aismono.net/mono-biz-app/educationclass/getCardList", requestBody)
                    .observe(this, object : HttpObserver<AismonoResponse<List<MyCardData.CardBean>>>() {
                        override fun onSuccess(response: AismonoResponse<List<MyCardData.CardBean>>) {
                            showMessage(GsonUtils.toJson(response))
                        }

                        override fun onFinish() {
                            hideLoading()
                        }

                    })
        }
        http_get.setOnClickListener {
            showLoading("加载中")
            mViewModel.get("https://www.wanandroid.com/banner/json")
                    .observe(this, object : HttpObserver<WanResponse<List<BannerData>>>() {
                        override fun onSuccess(response: WanResponse<List<BannerData>>) {
                            showMessage(GsonUtils.toJson(response))
                        }

                        override fun onFinish() {
                            hideLoading()
                        }

                    })
        }
        http_download.setOnClickListener {
            RxPermissions(this)
                    //读写通讯录权限
                    .request(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe {
                        if (it) {
                            mViewModel.download("https://m.mc.cn/Mc_V3.0.3.apk", CommonConstants.FilePath.PATH_CACHE + System.currentTimeMillis() + ".apk", object : DownloadListener {
                                override fun onStartDownload() {
                                    http_download.text = "开始下载"
                                    http_download.isEnabled = false
                                }

                                @SuppressLint("SetTextI18n")
                                override fun onProgress(progress: Int) {
                                    this@NetworkActivity.runOnUiThread {
                                        http_download.text = "下载进度-->$progress"
                                    }
                                    Log.e("", "下载进度-->$progress")

                                }

                                override fun onFinishDownload() {
                                    http_download.text = "下载完成"
                                    http_download.isEnabled = true
                                }

                                @SuppressLint("SetTextI18n")
                                override fun onFail(errorInfo: String) {
                                    http_download.text = "失败：$errorInfo"
                                    http_download.isEnabled = true
                                }
                            })
                        } else {
                            ToastUtils.showShort("拒绝了存储权限")
                            finish()
                        }
                    }
        }
    }

    override fun initData() {
    }

    companion object {
        fun starter(context: Context) {
            context.startActivity(Intent(context, NetworkActivity::class.java))
        }
    }
}
