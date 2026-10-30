package be.odisee.arysmauroprojectarysmauro.data

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val status: String,
    val message: String
)
