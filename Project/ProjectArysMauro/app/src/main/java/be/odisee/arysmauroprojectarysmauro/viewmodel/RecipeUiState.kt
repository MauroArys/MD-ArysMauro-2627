package be.odisee.arysmauroprojectarysmauro.viewmodel

import be.odisee.arysmauroprojectarysmauro.data.Recipe


data class RecipeUiState(
    val recipes: List<Recipe> = emptyList(),
    val huidigeAlias: String = "",
    val mijnRecepten: List<Recipe> = emptyList(),
    val sortType: SortType = SortType.LIKES,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

