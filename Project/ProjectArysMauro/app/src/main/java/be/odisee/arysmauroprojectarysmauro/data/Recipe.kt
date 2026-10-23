package be.odisee.arysmauroprojectarysmauro.data

data class Recipe(
    val title: String,
    val likes: Int,
    val date: String,
    val ingredients: String,
    val instructions: String,
    val time: Int
)
