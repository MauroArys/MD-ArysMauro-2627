package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.data.Recipe

// Dummy data voor Mijn Recepten
val myRecipesDummy = listOf(
    Recipe(
        title = "Pannenkoeken",
        likes = 5,
        date = "2026-10-20",
        ingredients = "Bloem, melk, eieren, suiker",
        instructions = "Mix alles en bak in de pan.",
        time = 15
    ),
    Recipe(
        title = "Croque Monsieur",
        likes = 3,
        date = "2026-10-18",
        ingredients = "Brood, kaas, ham",
        instructions = "Leg alles samen en bak.",
        time = 10
    ),
    Recipe(
        title = "Tomatensoep",
        likes = 8,
        date = "2026-10-10",
        ingredients = "Tomaten, ui, look, bouillon",
        instructions = "Koken en mixen.",
        time = 30
    )
)

@Composable
fun MyRecipesList(
    onRecipeClick: (Recipe) -> Unit   // ⭐ nodig voor detailpagina
) {

        LazyColumn {
            items(myRecipesDummy) { recipe ->
                RecipeCard(
                    title = recipe.title,
                    likes = recipe.likes,
                    date = recipe.date,
                    isInitiallyLiked = true,
                    onClick = { onRecipeClick(recipe) }   // ⭐ klik stuurt recept door
                )
            }
        }

}
