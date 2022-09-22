package com.android.myamazingapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.myamazingapplication.ui.theme.MyAmazingApplicationTheme
import com.android.myamazingapplication.view.HelloViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAmazingApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val viewModel = getViewModel<HelloViewModel>()
    val hello = viewModel.sayHello()
    Text(text = "Hello $name! and $hello")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAmazingApplicationTheme {
        Greeting("Android")
    }
}