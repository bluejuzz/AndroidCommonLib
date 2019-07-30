package com.company.commonlib.camerax

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.util.DisplayMetrics
import android.util.Rational
import android.view.View
import androidx.camera.core.CameraX
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysisConfig
import androidx.camera.core.PreviewConfig
import com.company.commonlib.R
import com.company.commonlibrary.base.BaseActivity
import com.company.commonlibrary.base.BaseViewModel
import com.google.zxing.Result
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_camera_x.*

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */
const val FLAGS_FULLSCREEN =
        View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

const val IMMERSIVE_FLAG_TIMEOUT = 500L

class CameraXBarcodeAnalyzerActivity : BaseActivity<BaseViewModel>(), ScanQRCodeCallback {
    override val layoutId = R.layout.activity_camera_x

    @SuppressLint("AutoDispose", "CheckResult")
    override fun initView() {
        val rxPermissions = RxPermissions(this)
        rxPermissions.request(android.Manifest.permission.CAMERA)
                .subscribe {
                    textureView.post {
                        val metrics = DisplayMetrics().also { textureView.display.getRealMetrics(it) }
                        val screenAspectRatio = Rational(metrics.widthPixels, metrics.heightPixels)

                        val previewConfig = PreviewConfig.Builder().apply {
                            setTargetAspectRatio(screenAspectRatio)
                            setTargetRotation(textureView.display.rotation)
                            setLensFacing(CameraX.LensFacing.BACK)
                        }.build()

                        val analysisConfig = ImageAnalysisConfig.Builder().apply {
                            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
                            val analyzerThread = HandlerThread("BarcodeAnalyzer").apply { start() }
                            analyzerHandler = Handler(analyzerThread.looper)
                            setCallbackHandler(analyzerHandler!!)
                            setTargetAspectRatio(screenAspectRatio)
                            setTargetRotation(textureView.display.rotation)
                            setLensFacing(CameraX.LensFacing.BACK)
                        }.build()

                        val preview = AutoFitPreviewBuilder.build(previewConfig, textureView)
                        analysis = ImageAnalysis(analysisConfig)
                        analysis!!.analyzer = QRCodeAnalyzer(this)
                        CameraX.bindToLifecycle(this@CameraXBarcodeAnalyzerActivity,
                                preview,
                                analysis)

                    }
                }
    }

    override fun initData() {

    }

    override fun scanQRCodeSuccess(result: Result) {
        showMessage(result.text)
        finish()
    }

    override fun scanQRCodeFail(error: String) {
        showMessage(error)
    }

    private var analyzerHandler: Handler? = null
    private var analysis: ImageAnalysis? = null

    override fun onResume() {
        super.onResume()
        // Before setting full screen flags, we must wait a bit to let UI settle; otherwise, we may
        // be trying to set app to immersive mode before it's ready and the flags do not stick
        textureView.postDelayed({
            textureView.systemUiVisibility = FLAGS_FULLSCREEN
        }, IMMERSIVE_FLAG_TIMEOUT)
    }

    override fun onDestroy() {
        analyzerHandler?.removeCallbacksAndMessages(null)
        analyzerHandler?.looper?.quitSafely()
        analysis?.analyzer = null
        super.onDestroy()
    }

    companion object {
        fun starter(context: Context) {
            context.startActivity(Intent(context, CameraXBarcodeAnalyzerActivity::class.java))
        }
    }
}

