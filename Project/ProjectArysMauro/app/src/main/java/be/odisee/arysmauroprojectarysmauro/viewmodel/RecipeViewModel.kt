package be.odisee.arysmauroprojectarysmauro.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.odisee.arysmauroprojectarysmauro.data.AddCommentRequest
import be.odisee.arysmauroprojectarysmauro.data.AddRecipeRequest
import be.odisee.arysmauroprojectarysmauro.data.AliasUpdate
import be.odisee.arysmauroprojectarysmauro.data.Comment
import be.odisee.arysmauroprojectarysmauro.data.LikeInfo
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.data.RecipesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.log

// Project Test - Code : SDFGBRTY

// Project Test - Finished
class RecipeViewModel : ViewModel() {

    //States
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()

    var likesForRecipe by mutableStateOf<List<LikeInfo>>(emptyList())
        private set

    var commentsForRecipe by mutableStateOf<List<Comment>>(emptyList())
        private set

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
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            delay(1500)
            try {
                val response = RecipesApi.retrofitService.getAllRecipes(headers)

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        recipes = response.data
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Kon recepten niet laden"
                    )
                }
            }
        }
    }

    //Gebruiker laden
    fun loadGebruiker() {
        viewModelScope.launch {
            try {
                val response = RecipesApi.retrofitService.getGebruiker(headers)

                _uiState.update {
                    it.copy(huidigeAlias = response.data.alias)
                    it.copy(ervaring = response.data.ervaring)
                    it.copy(requestCount = response.data.requestCount)
                }

            } catch (e: Exception) {
                println("Kon gebruiker niet laden: ${e.message}")
            }
        }
    }


    // Alias updaten
    fun updateAlias(nieuweAlias: String, nieuweErvaring: Int) {
        viewModelScope.launch {
            try {
                val response = RecipesApi.retrofitService.updateAlias(headers, AliasUpdate(nieuweAlias, nieuweErvaring))


                _uiState.update {
                    it.copy(huidigeAlias = response.data.alias)
                    it.copy(ervaring = response.data.ervaring)

                }

            } catch (e: Exception) {
                println("Kon alias en ervaring niet updaten: ${e.message}")

            }
        }
    }



    // Mijn recepten laden
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

    //Recept toevoegen
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
                    //stop deze coroutine
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
    //Recept liken
    fun likeRecipe(id: Int) {
        viewModelScope.launch {
            try {
                val response = RecipesApi.retrofitService.likeRecipe(headers, id)

                if (response.status == 200) {
                    val updatedAll = _uiState.value.recipes.map {
                        if (it.id == id) it.copy(
                            gebruikerGeliked = 1,
                            likes = it.likes + 1
                        ) else it
                    }
                    val updatedMine = _uiState.value.mijnRecepten.map {
                        if (it.id == id) it.copy(
                            gebruikerGeliked = 1,
                            likes = it.likes + 1
                        ) else it
                    }
                    _uiState.update {
                        it.copy(
                            recipes = updatedAll,
                            mijnRecepten = updatedMine
                        )
                    }
                    loadMijnRecepten(295)

                } else {
                    println("Kon recept niet liken: ${response.message}")
                }

            } catch (e: Exception) {
                println("Kon recept niet liken: ${e.message}")
            }
        }
    }
    //Recept unliken
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
                    loadMijnRecepten(295)

                } else {
                    println("Kon like niet verwijderen: ${response.message}")
                }

            } catch (e: Exception) {
                println("Kon like niet verwijderen: ${e.message}")
            }
        }
    }

    //Tonen wie er geliked heeft
    fun loadLikesForRecipe(id: Int) {
        viewModelScope.launch {
            try {
                val response = RecipesApi.retrofitService.getLikes(headers, id)

                if (response.status == 200) {
                    likesForRecipe = response.data
                } else {
                    println("Kon likes niet laden: ${response.status}")
                }

            } catch (e: Exception) {
                println("Kon likes niet laden: ${e.message}")
            }
        }
    }
    //Likes verwijderen
    fun clearLikes() {
        likesForRecipe = emptyList()
    }

    //Recept verwijderen
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

    fun loadCommentsForRecipe(id: Int) {
        viewModelScope.launch {
            try {
                val response =
                    RecipesApi.retrofitService.getComments(
                        headers,
                        id
                    )
                commentsForRecipe = response.data


            } catch (e: Exception) {
                println("Kon comments niet laden: ${e.message}")
            }
        }
    }

    fun clearComments() {
        commentsForRecipe = emptyList()
    }

    fun addComment(
        recipeId: Int,
        commentaar: String
    ) {
        viewModelScope.launch {
            try {

                val response =
                    RecipesApi.retrofitService.addComment(
                        headers,
                        recipeId,
                        AddCommentRequest(commentaar)
                    )

                if (response.status == 200) {
                    loadCommentsForRecipe(recipeId)
                }

            } catch (e: Exception) {
                println("Kon comment niet toevoegen: ${e.message}")
            }
        }
    }

    fun deleteComment(recipeId: Int) {
        viewModelScope.launch {
            try {
                val response =
                    RecipesApi.retrofitService.deleteComment(
                        headers,
                        recipeId
                    )

                if (response.status == 200) {
                    loadCommentsForRecipe(recipeId)
                }

            } catch (e: Exception) {
                println("Kon comment niet verwijderen: ${e.message}")
            }
        }
    }

    fun updateComment(
        recipeId: Int,
        commentId: Int,
        commentaar: String
    ) {
        viewModelScope.launch {
            try {

                val response =
                    RecipesApi.retrofitService.updateComment(
                        headers,
                        recipeId,
                        commentId,
                        AddCommentRequest(commentaar)
                    )

                if (response.status == 200) {
                    loadCommentsForRecipe(recipeId)
                }

            } catch (e: Exception) {
                println("Kon comment niet aanpassen: ${e.message}")
            }
        }
    }

    //Sorteer op likes of datum
    fun toggleSort() {
        _uiState.update {
            it.copy(
                sortType = if (it.sortType == SortType.LIKES) SortType.DATUM else SortType.LIKES
            )
        }
    }

}
