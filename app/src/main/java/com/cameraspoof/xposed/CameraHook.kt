package com.cameraspoof.xposed

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CaptureRequest
import android.media.Image
import android.media.ImageReader
import com.cameraspoof.utils.XposedLogger
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.ByteArrayOutputStream
import java.io.File

class CameraHook : IXposedHookLoadPackage {
    private val prefs by lazy {
        XSharedPreferences("com.cameraspoof", "app_configs").apply {
            makeWorldReadable()
        }
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName == "android" || lpparam.packageName == "com.cameraspoof") {
            return
        }

        try {
            prefs.reload()
            val isEnabled = prefs.getBoolean("${lpparam.packageName}_enabled", false)
            val imagePath = prefs.getString("${lpparam.packageName}_image", null)
            val spoofMode = prefs.getString("${lpparam.packageName}_mode", "ALL_CAMERAS")

            if (!isEnabled || imagePath == null) {
                return
            }

            val imageFile = File(imagePath)
            if (!imageFile.exists()) {
                XposedLogger.logError("Image file not found: $imagePath")
                return
            }

            XposedLogger.log("Hooking camera for ${lpparam.packageName}, mode: $spoofMode")

            hookCamera2API(lpparam, imagePath, spoofMode)
            hookCamera1API(lpparam, imagePath, spoofMode)

        } catch (e: Throwable) {
            XposedLogger.logError("Error in handleLoadPackage for ${lpparam.packageName}", e)
        }
    }

    private fun hookCamera2API(
        lpparam: XC_LoadPackage.LoadPackageParam,
        imagePath: String,
        spoofMode: String
    ) {
        try {
            val imageReaderClass = XposedHelpers.findClass(
                "android.media.ImageReader",
                lpparam.classLoader
            )

            XposedHelpers.findAndHookMethod(
                imageReaderClass,
                "acquireLatestImage",
                object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        try {
                            val originalImage = param.result as? Image ?: return

                            if (shouldSpoofCamera(param.thisObject, spoofMode)) {
                                val spoofedImage = createSpoofedImage(imagePath, originalImage)
                                if (spoofedImage != null) {
                                    param.result = spoofedImage
                                    XposedLogger.log("Camera2: Image spoofed for ${lpparam.packageName}")
                                }
                            }
                        } catch (e: Throwable) {
                            XposedLogger.logError("Error in acquireLatestImage hook", e)
                        }
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                imageReaderClass,
                "acquireNextImage",
                object : XC_MethodHook() {
                    override fun afterHookedMethod(param: MethodHookParam) {
                        try {
                            val originalImage = param.result as? Image ?: return

                            if (shouldSpoofCamera(param.thisObject, spoofMode)) {
                                val spoofedImage = createSpoofedImage(imagePath, originalImage)
                                if (spoofedImage != null) {
                                    param.result = spoofedImage
                                    XposedLogger.log("Camera2: Image spoofed for ${lpparam.packageName}")
                                }
                            }
                        } catch (e: Throwable) {
                            XposedLogger.logError("Error in acquireNextImage hook", e)
                        }
                    }
                }
            )

        } catch (e: Throwable) {
            XposedLogger.logError("Error hooking Camera2 API", e)
        }
    }

    private fun hookCamera1API(
        lpparam: XC_LoadPackage.LoadPackageParam,
        imagePath: String,
        spoofMode: String
    ) {
        try {
            val cameraClass = XposedHelpers.findClass(
                "android.hardware.Camera",
                lpparam.classLoader
            )

            XposedHelpers.findAndHookMethod(
                cameraClass,
                "setPreviewCallback",
                "android.hardware.Camera.PreviewCallback",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        try {
                            val originalCallback = param.args[0] ?: return

                            val wrappedCallback = createWrappedPreviewCallback(
                                originalCallback,
                                imagePath,
                                spoofMode,
                                param.thisObject,
                                lpparam.packageName
                            )

                            param.args[0] = wrappedCallback
                        } catch (e: Throwable) {
                            XposedLogger.logError("Error in setPreviewCallback hook", e)
                        }
                    }
                }
            )

            XposedHelpers.findAndHookMethod(
                cameraClass,
                "setOneShotPreviewCallback",
                "android.hardware.Camera.PreviewCallback",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        try {
                            val originalCallback = param.args[0] ?: return

                            val wrappedCallback = createWrappedPreviewCallback(
                                originalCallback,
                                imagePath,
                                spoofMode,
                                param.thisObject,
                                lpparam.packageName
                            )

                            param.args[0] = wrappedCallback
                        } catch (e: Throwable) {
                            XposedLogger.logError("Error in setOneShotPreviewCallback hook", e)
                        }
                    }
                }
            )

        } catch (e: Throwable) {
            XposedLogger.logError("Error hooking Camera1 API", e)
        }
    }

    private fun createWrappedPreviewCallback(
        originalCallback: Any,
        imagePath: String,
        spoofMode: String,
        camera: Any,
        packageName: String
    ): Any {
        return XposedHelpers.newInstance(
            XposedHelpers.findClass(
                "android.hardware.Camera\$PreviewCallback",
                originalCallback.javaClass.classLoader
            )
        ) { proxy, method, args ->
            try {
                if (method.name == "onPreviewFrame") {
                    val data = args[0] as ByteArray
                    val cameraObj = args[1]

                    if (shouldSpoofCamera1(cameraObj, spoofMode)) {
                        val spoofedData = loadImageAsYUV(imagePath, data.size)
                        if (spoofedData != null) {
                            args[0] = spoofedData
                            XposedLogger.log("Camera1: Preview spoofed for $packageName")
                        }
                    }
                }

                method.invoke(originalCallback, *args)
            } catch (e: Throwable) {
                XposedLogger.logError("Error in wrapped preview callback", e)
                method.invoke(originalCallback, *args)
            }
        }
    }

    private fun shouldSpoofCamera(imageReader: Any, spoofMode: String): Boolean {
        return when (spoofMode) {
            "FRONT_CAMERA" -> isFrontCamera(imageReader)
            "BACK_CAMERA" -> !isFrontCamera(imageReader)
            "ALL_CAMERAS" -> true
            else -> true
        }
    }

    private fun shouldSpoofCamera1(camera: Any, spoofMode: String): Boolean {
        return when (spoofMode) {
            "FRONT_CAMERA" -> isFrontCamera1(camera)
            "BACK_CAMERA" -> !isFrontCamera1(camera)
            "ALL_CAMERAS" -> true
            else -> true
        }
    }

    private fun isFrontCamera(imageReader: Any): Boolean {
        return try {
            val surface = XposedHelpers.getObjectField(imageReader, "mSurface")
            val cameraDevice = findCameraDeviceFromSurface(surface)
            if (cameraDevice != null) {
                val characteristics = XposedHelpers.getObjectField(
                    cameraDevice,
                    "mCharacteristics"
                ) as? CameraCharacteristics

                val lensFacing = characteristics?.get(CameraCharacteristics.LENS_FACING)
                lensFacing == CameraCharacteristics.LENS_FACING_FRONT
            } else {
                false
            }
        } catch (e: Throwable) {
            false
        }
    }

    private fun isFrontCamera1(camera: Any): Boolean {
        return try {
            val cameraInfo = XposedHelpers.callMethod(camera, "getCameraInfo")
            val facing = XposedHelpers.getIntField(cameraInfo, "facing")
            facing == 1 // CAMERA_FACING_FRONT
        } catch (e: Throwable) {
            false
        }
    }

    private fun findCameraDeviceFromSurface(surface: Any): Any? {
        return try {
            null
        } catch (e: Throwable) {
            null
        }
    }

    private fun createSpoofedImage(imagePath: String, originalImage: Image): Image? {
        return try {
            val bitmap = BitmapFactory.decodeFile(imagePath) ?: return null

            val width = originalImage.width
            val height = originalImage.height

            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)

            val yuvImage = convertBitmapToYUV(scaledBitmap)

            if (scaledBitmap != bitmap) {
                scaledBitmap.recycle()
            }
            bitmap.recycle()

            wrapYUVAsImage(yuvImage, width, height, originalImage.format)
        } catch (e: Throwable) {
            XposedLogger.logError("Error creating spoofed image", e)
            null
        }
    }

    private fun loadImageAsYUV(imagePath: String, targetSize: Int): ByteArray? {
        return try {
            val bitmap = BitmapFactory.decodeFile(imagePath) ?: return null
            val yuvData = convertBitmapToYUV(bitmap)
            bitmap.recycle()

            if (yuvData.size >= targetSize) {
                yuvData.copyOf(targetSize)
            } else {
                val paddedData = ByteArray(targetSize)
                System.arraycopy(yuvData, 0, paddedData, 0, yuvData.size)
                paddedData
            }
        } catch (e: Throwable) {
            XposedLogger.logError("Error loading image as YUV", e)
            null
        }
    }

    private fun convertBitmapToYUV(bitmap: Bitmap): ByteArray {
        val width = bitmap.width
        val height = bitmap.height
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        val yuvSize = width * height * 3 / 2
        val yuv = ByteArray(yuvSize)

        var yIndex = 0
        var uvIndex = width * height

        for (j in 0 until height) {
            for (i in 0 until width) {
                val pixel = pixels[j * width + i]

                val r = (pixel shr 16) and 0xff
                val g = (pixel shr 8) and 0xff
                val b = pixel and 0xff

                val y = ((66 * r + 129 * g + 25 * b + 128) shr 8) + 16
                val u = ((-38 * r - 74 * g + 112 * b + 128) shr 8) + 128
                val v = ((112 * r - 94 * g - 18 * b + 128) shr 8) + 128

                yuv[yIndex++] = y.coerceIn(0, 255).toByte()

                if (j % 2 == 0 && i % 2 == 0) {
                    yuv[uvIndex++] = u.coerceIn(0, 255).toByte()
                    yuv[uvIndex++] = v.coerceIn(0, 255).toByte()
                }
            }
        }

        return yuv
    }

    private fun wrapYUVAsImage(
        yuvData: ByteArray,
        width: Int,
        height: Int,
        format: Int
    ): Image? {
        return try {
            null
        } catch (e: Throwable) {
            XposedLogger.logError("Error wrapping YUV as Image", e)
            null
        }
    }
}
