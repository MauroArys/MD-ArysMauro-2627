package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.data.dummyRecipes

@Composable
fun RecipesList(
    modifier: Modifier = Modifier,
    onRecipeClick: (Recipe) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(dummyRecipes) { recipe ->
            RecipeCard(
                title = recipe.titel,
                likes = recipe.likes,
                date = recipe.datumAangemaakt,
                isInitiallyLiked = recipe.gebruikerGeliked == 1,
                onClick = { onRecipeClick(recipe) }
            )
        }
    }
}
