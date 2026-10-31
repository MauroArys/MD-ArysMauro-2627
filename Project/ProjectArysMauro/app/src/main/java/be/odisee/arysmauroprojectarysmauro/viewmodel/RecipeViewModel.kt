package be.odisee.arysmauroprojectarysmauro.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.odisee.arysmauroprojectarysmauro.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()

    var backendErrors by mutableStateOf<List<String>>(emptyList())
        private set


    // API headers + token
    private val headers = HashMap<String, String>()
    private val apiKey = "6c5m9qe0rrprghsvqr60fef2gxawrh6"

    init {
        headers["Authorization"] = "Bearer $apiKey"
        reload()
        loadGebruiker()
    }

    // GET alle recepten
    fun reload() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            try {

                val response = RecipesApi.retrofitService.getAllRecipes(headers)

                println("Aantal recepten: ${response.data.size}")

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        recipes = response.data
                    )
                }

            } catch (e: Exception) {
                println("Fout bij ophalen: ${e.message}")
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Kon recepten niet laden"
                    )
                }
            }
        }
    }
    fun loadGebruiker() {
        viewModelScope.launch {
            try {
                val response = RecipesApi.retrofitService.getGebruiker(headers)

                _uiState.update {
                    it.copy(huidigeAlias = response.data.alias)
                }

            } catch (e: Exception) {
                println("Kon gebruiker niet laden: ${e.message}")
            }
        }
    }



    fun updateAlias(nieuweAlias: String) {
        viewModelScope.launch {
            try {
                RecipesApi.retrofitService.updateAlias(headers, AliasUpdate(nieuweAlias))


                _uiState.update {
                    it.copy(huidigeAlias = nieuweAlias)
                }


            } catch (e: Exception) {
                println("Kon alias niet updaten: ${e.message}")
            }
        }
    }

    fun loadMijnRecepten(gebruikerId: Int) {
        viewModelScope.launch {
            try {
                val response = RecipesApi.retrofitService.getReceptenVanGebruiker(headers, gebruikerId)

                _uiState.update {
                    it.copy(mijnRecepten = response.data)
                }

            } catch (e: Exception) {
                println("Kon mijn recepten niet laden: ${e.message}")
            }
        }
    }

    fun addRecipe(
        titel: String,
        ingredienten: String,
        stappen: String,
        tijd: Int,
        afbeelding: String? = null
    ) {
        viewModelScope.launch {
            try {
                val request = AddRecipeRequest(
                    titel = titel,
                    ingredienten = ingredienten,
                    stappen = stappen,
                    tijd = tijd,
                    afbeelding = afbeelding
                )

                val response = RecipesApi.retrofitService.addRecipe(headers, request)

                if (response.ontbrekendeVelden != null) {
                    backendErrors = response.ontbrekendeVelden
                    return@launch
                }

                backendErrors = emptyList()

                if (response.status == 200 && response.id != null) {
                    println("Recept toegevoegd met id: ${response.id}")
                    loadMijnRecepten(295)
                }

            } catch (e: Exception) {
                println("Kon recept niet toevoegen: ${e.message}")
            }
        }
    }
    fun likeRecipe(id: Int) {
        viewModelScope.launch {
            try {
                val response = RecipesApi.retrofitService.likeRecipe(headers, id)

                if (response.status == 200) {
                    reload()
                    loadMijnRecepten(295)
                } else {
                    println("Kon recept niet liken: ${response.message}")
                }

            } catch (e: Exception) {
                println("Kon recept niet liken: ${e.message}")
            }
        }
    }

    fun deleteLike(id: Int) {
        viewModelScope.launch {
            try {
                val response = RecipesApi.retrofitService.deleteLike(headers, id)

                if (response.status == 200) {

                    val updatedAll = _uiState.value.recipes.map {
                        if (it.id == id) it.copy(
                            gebruikerGeliked = 0,
                            likes = it.likes - 1
                        ) else it
                    }

                    val updatedMine = _uiState.value.mijnRecepten.map {
                        if (it.id == id) it.copy(
                            gebruikerGeliked = 0,
                            likes = it.likes - 1
                        ) else it
                    }

                    _uiState.update {
                        it.copy(
                            recipes = updatedAll,
                            mijnRecepten = updatedMine
                        )
                    }

                } else {
                    println("Kon like niet verwijderen: ${response.message}")
                }

            } catch (e: Exception) {
                println("Kon like niet verwijderen: ${e.message}")
            }
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            try {
                val response = RecipesApi.retrofitService.deleteRecipe(headers, recipe.id)

                if (response.isSuccessful) {
                    val updatedAll = _uiState.value.recipes.filter { it.id != recipe.id }
                    val updatedMine = _uiState.value.mijnRecepten.filter { it.id != recipe.id }

                    _uiState.update {
                        it.copy(
                            recipes = updatedAll,
                            mijnRecepten = updatedMine
                        )
                    }
                } else {
                    println("Kon recept niet verwijderen: HTTP ${response.code()}")
                }

            } catch (e: Exception) {
                println("Kon recept niet verwijderen: ${e.message}")
            }
        }
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

    fun getRecipeById(id: Int): Recipe? {
        return _uiState.value.recipes.find { it.id == id }
    }
}
