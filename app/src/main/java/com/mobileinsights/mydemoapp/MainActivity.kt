package com.mobileinsights.mydemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mobileinsights.mydemoapp.ui.theme.MyDemoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDemoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckInternetConnection()
                }
            }
        }
    }
}

@Composable
fun CheckInternetConnection(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "Do I have Internet Permission?")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDemoAppTheme {
        CheckInternetConnection()
    }
}