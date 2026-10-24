package be.odisee.arysmauroprojectarysmauro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.data.dummyRecipes

class RecipeViewModel : ViewModel() {

    // State list met alle recepten
    private val _recipes = mutableStateListOf<Recipe>()
    val recipes: List<Recipe> get() = _recipes

    // Sorteertype: "likes" of "datum"
    var sortType = mutableStateOf("likes")

    init {
        // Laad dummy data in de state list
        _recipes.addAll(dummyRecipes)
    }

    // Like toggelen
    fun toggleLike(recipe: Recipe) {
        val index = _recipes.indexOf(recipe)
        if (index != -1) {
            val updated = recipe.copy(
                gebruikerGeliked = if (recipe.gebruikerGeliked == 1) 0 else 1,
                likes = if (recipe.gebruikerGeliked == 1) recipe.likes - 1 else recipe.likes + 1
            )
            _recipes[index] = updated
        }
    }

    // Sorteerwijze wisselen
    fun toggleSort() {
        sortType.value = if (sortType.value == "likes") "datum" else "likes"
    }

    // Gesorteerde lijst teruggeven
    fun sortedRecipes(): List<Recipe> {
        return when (sortType.value) {
            "likes" -> recipes.sortedByDescending { it.likes }
            else -> recipes.sortedByDescending { it.datumAangemaakt }
        }
    }
}
