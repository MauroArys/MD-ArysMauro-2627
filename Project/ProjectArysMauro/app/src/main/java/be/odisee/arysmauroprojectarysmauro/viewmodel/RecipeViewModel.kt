package be.odisee.arysmauroprojectarysmauro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.data.dummyRecipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()

    init {
        // Dummy data laden
        _uiState.update { it.copy(recipes = dummyRecipes) }
    }

    fun toggleLike(recipe: Recipe) {
        val updatedList = _uiState.value.recipes.map {
            if (it.id == recipe.id) {
                it.copy(
                    gebruikerGeliked = if (it.gebruikerGeliked == 1) 0 else 1,
                    likes = if (it.gebruikerGeliked == 1) it.likes - 1 else it.likes + 1
                )
            } else it
        }

        _uiState.update { it.copy(recipes = updatedList) }
    }

    fun toggleSort() {
        _uiState.update {
            it.copy(
                sortType = if (it.sortType == SortType.LIKES) SortType.DATUM else SortType.LIKES
            )
        }
    }

    fun sortedRecipes(): List<Recipe> {
        return when (_uiState.value.sortType) {
            SortType.LIKES -> _uiState.value.recipes.sortedByDescending { it.likes }
            SortType.DATUM -> _uiState.value.recipes.sortedByDescending { it.datumAangemaakt }
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        val updatedList = _uiState.value.recipes.filter { it.id != recipe.id }
        _uiState.update { it.copy(recipes = updatedList) }
    }

    fun getRecipeById(id: Int): Recipe? {
        return _uiState.value.recipes.find { it.id == id }
    }
}
