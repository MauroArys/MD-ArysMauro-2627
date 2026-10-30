package be.odisee.arysmauroprojectarysmauro.data

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Int,
    val titel: String,
    val ingredienten: String,
    val stappen: String,
    val tijd: Int,
    val datumAangemaakt: String,
    val gebruikerAangemaaktId: Int,
    val gebruikerAangemaaktAlias: String,
    val afbeelding: String?,
    var likes: Int,
    val commentaar: Int,
    var gebruikerGeliked: Int
)

