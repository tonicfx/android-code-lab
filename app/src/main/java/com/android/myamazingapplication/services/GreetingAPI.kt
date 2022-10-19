package com.android.myamazingapplication.services

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class GreetingAPI(private val client: HttpClient) {

    suspend fun getHtml(): String {
        val response = client.get("https://ktor.io/docs")
        return response.bodyAsText()
    }

    suspend fun search(address: String, postalCode: String) : FeatureCollectionDto {
        return client.get("https://api-adresse.data.gouv.fr/search/") {
            url {
                parameters.append("q", address)
                parameters.append("postcode", postalCode)
            }
        }.body<FeatureCollectionDto>()
    }
}