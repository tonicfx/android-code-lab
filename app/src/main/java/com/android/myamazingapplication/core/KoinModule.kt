package com.android.myamazingapplication.core

import com.android.myamazingapplication.domain.CustomRepoImpl
import com.android.myamazingapplication.domain.HelloRepository
import com.android.myamazingapplication.domain.HelloRepositoryImpl
import com.android.myamazingapplication.view.HelloViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // single instance of HelloRepository
    single<HelloRepository> { CustomRepoImpl() }

    viewModel() {
        HelloViewModel(get())
    }
}