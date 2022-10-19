package com.android.myamazingapplication.view

import androidx.lifecycle.ViewModel
import com.android.myamazingapplication.core.Status
import com.android.myamazingapplication.domain.HelloRepository
import com.android.myamazingapplication.services.FeatureCollectionDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class HelloViewModelState(
    var doc: String? = null,
    var isLoading: Boolean = false,
)


data class AddressViewModelState(
    var featureCollectionDto: FeatureCollectionDto? = null,
    var isLoading: Boolean = false,
)

class HelloViewModel(private val repository : HelloRepository): ViewModel() {
    fun sayHelloByFlow(): Flow<HelloViewModelState> = flow {
        repository.giveHello().collect { resource ->
            val state = HelloViewModelState(
                doc = resource.data,
                isLoading = resource.status == Status.LOADING
            )
            emit(state)
        }
    }

    fun searchAddress(address : String, postalCode: String): Flow<AddressViewModelState> {
        return flow {
            repository.searchAddress(address, postalCode).collect {
                val state = AddressViewModelState(
                    featureCollectionDto = it.data,
                    isLoading = it.status == Status.LOADING
                )
                emit(state)
            }
        }
    }
}