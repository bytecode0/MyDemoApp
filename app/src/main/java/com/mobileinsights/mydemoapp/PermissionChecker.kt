package com.mobileinsights.mydemoapp

import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED

class PermissionChecker(
    private val context: Context
) {
    fun cameraPermissionGranted() : Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.CAMERA
        ) == PERMISSION_GRANTED
    }
}