package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.odisee.arysmauroprojectarysmauro.data.Recipe

@Composable
fun RecipesList(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    onLikeToggle: (Recipe) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(recipes) { recipe ->
            RecipeCard(
                title = recipe.titel,
                likes = recipe.likes,
                date = recipe.datumAangemaakt,
                isInitiallyLiked = recipe.gebruikerGeliked == 1,
                onClick = { onRecipeClick(recipe) },
                onLikeToggle = { onLikeToggle(recipe) }
            )
        }
    }
}






