package be.odisee.arysmauroprojectarysmauro.components.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import be.odisee.arysmauroprojectarysmauro.components.recipes.RecipesList
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun RecipesListScreen(viewModel: RecipeViewModel = viewModel()) {

    val uiState by viewModel.uiState.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.reload()
    }

    RecipesList(
        recipes = uiState.recipes,
        onRecipeClick = {  },
        onLike = { recipe ->
            viewModel.likeRecipe(recipe.id)
        }
    )
}
