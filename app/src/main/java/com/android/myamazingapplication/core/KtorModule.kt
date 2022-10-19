package com.android.myamazingapplication.core

import android.util.Log
import com.android.myamazingapplication.services.GreetingAPI
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import io.ktor.client.engine.android.*
import org.koin.dsl.module

val ktorModule = module {
    single { Android.create() }
    single { createJson() }
    single { createHttpClient(get(), get(), enableNetworkLogs = true) }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    single<GreetingAPI> { GreetingAPI(get()) }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun createHttpClient(httpClientEngine: HttpClientEngine, json: Json, enableNetworkLogs: Boolean) =
    HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(json)
        }
        if (enableNetworkLogs) {
            install(Logging) {
                logger = CustomHttpLogger()
                level = LogLevel.ALL
            }
        }
    }


class CustomHttpLogger() : Logger {
    override fun log(message: String) {
        Log.d("Http Logger", message)
    }
}