package com.company.commonlib

import android.view.View
import com.company.commonlib.camerax.CameraXBarcodeAnalyzerActivity
import com.company.commonlib.contacts.ContactsTestActivity
import com.company.commonlib.html.HtmlTestActivity
import com.company.commonlib.network.AismonoResponse
import com.company.commonlib.network.BannerData
import com.company.commonlib.network.MyCardData
import com.company.commonlib.network.WanResponse
import com.company.commonlibrary.base.*
import com.company.commonlibrary.bean.RequestEntity
import com.company.commonlibrary.retrofit.BaseHttpModel
import com.company.commonlibrary.retrofit.HttpObserver
import com.company.commonlibrary.util.NetworkChangeUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */
class MainActivity : BaseActivity<BaseHttpModel>() {

    override val layoutId = R.layout.activity_main

    override fun initView() {
//        NetworkChangeUtils.registerListener(changeListener = NetworkChangeUtils.NetworkChangeListener())
        findViewById<View>(R.id.test_http).setOnClickListener {
            showLoading("加载中")
            val requestBody = mViewModel.getRequestBody(RequestEntity<Map<String, Any>>(hashMapOf("phone" to 18279727279L)))
            mViewModel.post("https://http.aismono.net/mono-biz-app/educationclass/getCardList", requestBody)
                    .observe(this, object : HttpObserver<AismonoResponse<List<MyCardData.CardBean>>>() {
                        override fun onSuccess(response: AismonoResponse<List<MyCardData.CardBean>>) {
                        }

                        override fun onFinish() {
                            hideLoading()
                        }

                    })

            mViewModel.get("https://www.wanandroid.com/banner/json")
                    .observe(this, object : HttpObserver<WanResponse<List<BannerData>>>() {
                        override fun onSuccess(response: WanResponse<List<BannerData>>) {
                        }

                        override fun onFinish() {
                            hideLoading()
                        }

                    })
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
        super.onDestroy()
        NetworkChangeUtils.unRegisterListener()
    }
}

