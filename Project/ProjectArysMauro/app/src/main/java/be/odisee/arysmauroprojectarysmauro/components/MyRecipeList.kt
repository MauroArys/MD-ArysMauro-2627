package be.odisee.arysmauroprojectarysmauro.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.ui.components.RecipeCard

@Composable
fun MyRecipeList(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    onLikeToggle: (Recipe) -> Unit,
    onDelete: (Recipe) -> Unit,
    huidigeGebruiker: String
) {
    LazyColumn(modifier = modifier) {
        items(recipes) { recipe ->

            val isEigenRecept = recipe.gebruikerAangemaaktAlias == huidigeGebruiker

            RecipeCard(
                title = recipe.titel,
                likes = recipe.likes,
                date = recipe.datumAangemaakt,
                isInitiallyLiked = recipe.gebruikerGeliked == 1,
                onClick = { onRecipeClick(recipe) },
                onLikeToggle = { onLikeToggle(recipe) },
                showDelete = isEigenRecept,
                onDelete = if (isEigenRecept) {
                    { onDelete(recipe) }
                } else null
            )
        }
    }
}


