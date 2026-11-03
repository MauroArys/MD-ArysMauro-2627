package be.odisee.arysmauroprojectarysmauro.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import be.odisee.arysmauroprojectarysmauro.components.recipes.RecipesList
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun RecipesListScreen(viewModel: RecipeViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    // API call bij openen scherm
    LaunchedEffect(Unit) {
        viewModel.reload()
    }

    // Loading indicator
    if (uiState.isLoading) {
        Box(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    // Lijst tonen
    RecipesList(
        recipes = uiState.recipes,
        huidigeGebruiker = "Mauro",
        onRecipeClick = { },
        onLike = { recipe ->
            viewModel.likeRecipe(recipe.id)
        },
        onDelete = { recipe ->
            viewModel.deleteRecipe(recipe)
        }
    )
}
