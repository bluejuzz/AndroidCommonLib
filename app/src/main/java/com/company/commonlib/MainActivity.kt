package com.company.commonlib

import android.view.View
import androidx.lifecycle.Lifecycle
import com.company.commonlib.camerax.CameraXBarcodeAnalyzerActivity
import com.company.commonlib.network.AismonoResponse
import com.company.commonlib.network.BannerData
import com.company.commonlib.network.MyCardData
import com.company.commonlib.network.WanResponse

import com.company.commonlibrary.base.BaseActivity
import com.company.commonlibrary.base.BaseModel
import com.company.commonlibrary.base.BasePresenter
import com.company.commonlibrary.base.IModel
import com.company.commonlibrary.base.IPresenter
import com.company.commonlibrary.base.IView
import com.company.commonlibrary.bean.RequestEntity
import com.company.commonlibrary.retrofit.BaseCallback
import com.company.commonlibrary.retrofit.BaseHttpModel
import com.company.commonlibrary.util.NetworkChangeUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */
class MainActivity : BaseActivity<IPresenter>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override val presenter: IPresenter
        get() = BasePresenter<IView, IModel>(this, BaseModel())

    override fun initView() {
//        NetworkChangeUtils.registerListener(changeListener = NetworkChangeUtils.NetworkChangeListener())
        findViewById<View>(R.id.test_http).setOnClickListener {
            showLoading("加载中")
            val requestBody =
                    BaseHttpModel.instance.getRequestBody(RequestEntity<Map<String, Any>>(hashMapOf("phone" to 18279727279L)))
            BaseHttpModel.instance.post(
                    "https://http.aismono.net/mono-biz-app/educationclass/getCardList",
                    requestBody,
                    bindLifecycle(Lifecycle.Event.ON_DESTROY),
                    object : BaseCallback<AismonoResponse<List<MyCardData.CardBean>>>() {

                        override fun onSuccess(response: AismonoResponse<List<MyCardData.CardBean>>) {
                            val body = response.body
                        }

                        override fun onFinish() {

                        }
                    })
            BaseHttpModel.instance["https://www.wanandroid.com/banner/json",
                    null,
                    bindLifecycle(Lifecycle.Event.ON_DESTROY),
                    object : BaseCallback<WanResponse<List<BannerData>>>() {

                        override fun onSuccess(response: WanResponse<List<BannerData>>) {
                            val data = response.data
                        }

                        override fun onFinish() {

                        }
                    }]
        }
        test_camerax.setOnClickListener {
            CameraXBarcodeAnalyzerActivity.starter(this)
        }
    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        NetworkChangeUtils.unRegisterListener()
    }
}
