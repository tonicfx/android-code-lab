package com.android.myamazingapplication.domain

interface HelloRepository {
    fun giveHello(): String
}

class HelloRepositoryImpl() : HelloRepository {
    override fun giveHello() = "Hello Koin"
}

class CustomRepoImpl() : HelloRepository {
    override fun giveHello() = "Hello Custom Koin"
}
