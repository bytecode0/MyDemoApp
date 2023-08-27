package com.mobileinsights.mydemoapp

import android.content.Context
import android.text.TextUtils
import androidx.core.app.NotificationManagerCompat
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PermissionCheckerTest {

    @Test
    fun `Returns true if camera permission granted`() {
        val context = mockk<Context>()
        mockkStatic(TextUtils::class)
        mockkStatic(NotificationManagerCompat::class)
        val expectedValue = true
        val permissionChecker = PermissionChecker(context = context)

        every { TextUtils.equals(any(), any()) } returns true
        every { NotificationManagerCompat.from(context).areNotificationsEnabled() } returns true

        val isGranted = permissionChecker.cameraPermissionGranted()
        assertEquals(expectedValue, isGranted)
    }
}