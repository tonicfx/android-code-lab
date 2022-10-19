package com.android.myamazingapplication.view

import androidx.lifecycle.ViewModel
import com.android.myamazingapplication.core.Status
import com.android.myamazingapplication.domain.HelloRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class HelloViewModelState(
    var doc: String? = null,
    var isLoading: Boolean = false,
)

class HelloViewModel(private val repository : HelloRepository): ViewModel() {
    fun sayHelloByFlow(): Flow<HelloViewModelState> = flow {
        repository.giveHello().collect {
            val state = HelloViewModelState(doc = it.data, isLoading = it.status == Status.LOADING)
            emit(state)
        }
    }

}