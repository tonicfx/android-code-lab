package com.android.myamazingapplication.domain

import com.android.myamazingapplication.core.Resource
import com.android.myamazingapplication.services.GreetingAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


interface HelloRepository {
    suspend fun giveHello(): Flow<Resource<String>>
}

class HelloRepositoryImpl(val greetingAPI: GreetingAPI) : HelloRepository {
    override suspend fun giveHello(): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading())
            emit(Resource.Success(greetingAPI.getHtml()))
        }

    }
}
