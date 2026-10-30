package be.odisee.arysmauroprojectarysmauro.data

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val status: Int,
    val data: GebruikerData
)

@Serializable
data class GebruikerData(
    val id: Int,
    val naam: String,
    val voornaam: String,
    val nummer: String,
    val laatstActief: String,
    val alias: String
)

@Serializable
data class StatusMessageResponse(
    val status: Int,
    val message: String
)



