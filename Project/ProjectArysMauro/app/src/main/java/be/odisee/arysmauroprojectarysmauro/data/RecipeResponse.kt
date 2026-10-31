package be.odisee.arysmauroprojectarysmauro.data

import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    val status: Int,
    val data: List<Recipe>
)
@Serializable
data class MijnReceptenResponse(
    val status: Int,
    val data: List<Recipe>
)

@Serializable
data class AddRecipeRequest(
    val titel: String,
    val ingredienten: String,
    val stappen: String,
    val tijd: Int,
    val afbeelding: String? = null
)
@Serializable
data class AddRecipeResponse(
    val status: Int,
    val message: String,
    val id: Int? = null,
    val ontbrekendeVelden: List<String>? = null
)
@Serializable
data class LikeResponse(
    val status: Int,
    val message: String
)

