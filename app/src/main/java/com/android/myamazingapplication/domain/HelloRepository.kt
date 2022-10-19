package com.android.myamazingapplication.domain

import com.android.myamazingapplication.core.Resource
import com.android.myamazingapplication.services.FeatureCollectionDto
import com.android.myamazingapplication.services.GreetingAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface HelloRepository {
    fun searchAddress(address: String, postalCode: String): Flow<Resource<FeatureCollectionDto>>

    fun giveHello(): Flow<Resource<String>>
}

class HelloRepositoryImpl(val greetingAPI: GreetingAPI) : HelloRepository {
    override fun giveHello(): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading())
            emit(Resource.Success(greetingAPI.getHtml()))
        }
    }

    override fun searchAddress(address: String, postalCode: String) : Flow<Resource<FeatureCollectionDto>> {
        return flow {
            emit(Resource.Loading())
            emit(Resource.Success(greetingAPI.search(address, postalCode)))
        }
    }
}
