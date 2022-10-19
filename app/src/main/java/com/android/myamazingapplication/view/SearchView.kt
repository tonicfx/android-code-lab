package com.android.myamazingapplication.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.android.myamazingapplication.services.FeatureDto
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchView() {
    val scope = rememberCoroutineScope()
    val viewModel = getViewModel<HelloViewModel>()
    var state by remember {
        mutableStateOf(AddressViewModelState())
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        SearchAddress(isLoading = state.isLoading, searchHandler = { address, postCode ->
            scope.launch {
                viewModel.searchAddress(address, postCode).collect { addressViewModelState ->
                    state = addressViewModelState
                }
            }
        })

        state.featureCollectionDto?.let {
            if (it.features.isNotEmpty()) {
                SearchResult(it.features[0])
            }
        }
    }
}

@Composable
fun SearchAddress(isLoading: Boolean, searchHandler: (address: String, postCode: String) -> Unit) {

    var address by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = address,
                    label = { Text("Address") },
                    onValueChange = { address = it })
                OutlinedTextField(
                    value = postalCode,
                    label = { Text("Postal Code") },
                    onValueChange = { postalCode = it })
                Button(onClick = {
                    searchHandler(address, postalCode)
                }) {
                    Text(text = "Search")
                }
            }
        }
    }
}

@Composable
fun SearchResult(feature: FeatureDto) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = feature.properties.label)
        Text(text = feature.properties.postcode)
        Text(text = feature.properties.city)
    }
}