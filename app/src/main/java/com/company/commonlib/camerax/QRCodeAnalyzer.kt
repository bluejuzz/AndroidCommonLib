package com.company.commonlib.camerax

import android.graphics.ImageFormat
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer


/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */
class QRCodeAnalyzer(private var scanQRCodeCallback: ScanQRCodeCallback?) : ImageAnalysis.Analyzer {

    private val reader: MultiFormatReader = MultiFormatReader().apply {
        val map = mapOf<DecodeHintType, Collection<BarcodeFormat>>(
                Pair(DecodeHintType.POSSIBLE_FORMATS, arrayListOf(BarcodeFormat.QR_CODE))
        )
        setHints(map)
    }


    override fun analyze(image: ImageProxy, rotationDegrees: Int) {
        //YUV_420 is normally the input type here, but other YUV types are also supported in theory
        if (ImageFormat.YUV_420_888 != image.format && ImageFormat.YUV_422_888 != image.format && ImageFormat.YUV_444_888 != image.format) {
            Log.e("BarcodeAnalyzer", "expect YUV, now = ${image.format}")
            return
        }
        val buffer = image.planes[0].buffer
        val data = ByteArray(buffer.remaining())
        val height = image.height
        val width = image.width
        buffer.get(data)
        //TODO 调整crop的矩形区域，目前是全屏（全屏有更好的识别体验，但是在部分手机上可能OOM）
        val source = PlanarYUVLuminanceSource(data, width, height, 0, 0, width, height, false)

        val bitmap = BinaryBitmap(HybridBinarizer(source))

        try {
            val result = reader.decode(bitmap)
            Log.e("BarcodeAnalyzer", "resolved!!! = $result")
            scanQRCodeCallback?.scanQRCodeSuccess(result)
        } catch (e: Exception) {
            Log.d("BarcodeAnalyzer", "Error decoding barcode")
            e.message?.let { scanQRCodeCallback?.scanQRCodeFail(it) }
        }

    }
}
