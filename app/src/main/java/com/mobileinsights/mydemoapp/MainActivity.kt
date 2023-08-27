package com.mobileinsights.mydemoapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.mobileinsights.mydemoapp.ui.theme.MyDemoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDemoAppTheme {
                val context = LocalContext.current
                val permissionChecker = PermissionChecker(context = context)
                val cameraPermissionState = remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RequestPermissionComponent(
                            permissionChecker = permissionChecker,
                            cameraPermissionState = cameraPermissionState
                        )
                        ShowPermissionsStateComponent(
                            cameraPermissionState = cameraPermissionState
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RequestPermissionComponent(
    modifier: Modifier = Modifier,
    permissionChecker: PermissionChecker,
    cameraPermissionState: MutableState<Boolean>
) {

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            cameraPermissionState.value = isGranted
        }
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {
                if (permissionChecker.cameraPermissionGranted()) {
                    cameraPermissionState.value = permissionChecker.cameraPermissionGranted()
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
            }
        ) {
            Text(text = "¡Request Camera Permission Now!")
        }
    }
}

@Composable
fun ShowPermissionsStateComponent(
    modifier: Modifier = Modifier,
    cameraPermissionState: MutableState<Boolean>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = getPermissionMessage(cameraPermissionState.value))
    }
}

private fun getPermissionMessage(hasPermission: Boolean) : String =
    if (hasPermission) "Camera permission granted" else "Camera permission denied"

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDemoAppTheme {
        val context = LocalContext.current
        val permissionChecker = PermissionChecker(context = context)
        val cameraPermissionState = remember { mutableStateOf(false) }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RequestPermissionComponent(
                    permissionChecker = permissionChecker,
                    cameraPermissionState = cameraPermissionState
                )
                ShowPermissionsStateComponent(
                    cameraPermissionState = cameraPermissionState
                )
            }
        }
    }
}