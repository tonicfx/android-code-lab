package com.android.myamazingapplication.services

@kotlinx.serialization.Serializable
data class FeatureCollectionDto(
    val type: String,
    val version: String,
    val features: Array<FeatureDto>,
    val attribution: String,
    val licence: String
)

@kotlinx.serialization.Serializable
data class FeatureDto(
    val type : String,
    val geometry : GeometryDto,
    val properties : PropertiesDto
)

@kotlinx.serialization.Serializable
data class GeometryDto(
    val type : String,
    val coordinates : Array<Double>
)

@kotlinx.serialization.Serializable
data class PropertiesDto(
    val label : String,
    val postcode : String,
    val city : String
)