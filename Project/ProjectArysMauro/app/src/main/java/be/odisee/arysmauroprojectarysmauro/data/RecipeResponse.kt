package be.odisee.arysmauroprojectarysmauro.data

import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val status: Int,
    val data: List<Recipe>
)
