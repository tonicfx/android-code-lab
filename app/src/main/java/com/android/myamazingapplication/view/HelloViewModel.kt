package com.android.myamazingapplication.view

import androidx.lifecycle.ViewModel
import com.android.myamazingapplication.domain.HelloRepository

class HelloViewModel(private val repository : HelloRepository): ViewModel() {
    fun sayHello() : String {
        return "${repository.giveHello()}"
    }
}