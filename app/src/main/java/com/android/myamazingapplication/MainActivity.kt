package com.android.myamazingapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.myamazingapplication.ui.theme.MyAmazingApplicationTheme
import com.android.myamazingapplication.view.AddressViewModelState
import com.android.myamazingapplication.view.HelloViewModel
import com.android.myamazingapplication.view.HelloViewModelState
import com.android.myamazingapplication.view.SearchView
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
                    SearchView()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val viewModel = getViewModel<HelloViewModel>()
    val state by remember(viewModel) {
        viewModel.searchAddress("89 rue Jules Guesde", "29200")
    }.collectAsState(initial = AddressViewModelState())

    Text(text = "Hello $name! and  ${state.isLoading} and ${state.featureCollectionDto}")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyAmazingApplicationTheme {
        Greeting("Android")
    }
}