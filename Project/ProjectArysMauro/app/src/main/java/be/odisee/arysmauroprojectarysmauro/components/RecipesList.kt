package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.data.Recipe

// ⭐ Jouw echte dummy data (zoals in MyRecipesList)
val dummyRecipes = listOf(
    Recipe(
        title = "Spaghetti Bolognese",
        likes = 12,
        date = "2026-10-01",
        ingredients = "Gehakt, tomaten, ui, look, pasta",
        instructions = "Koken en mengen.",
        time = 30
    ),
    Recipe(
        title = "Chicken Curry",
        likes = 8,
        date = "2026-10-05",
        ingredients = "Kip, curry, room, rijst",
        instructions = "Kip bakken, saus maken.",
        time = 25
    ),
    Recipe(
        title = "Pancakes",
        likes = 20,
        date = "2026-10-10",
        ingredients = "Bloem, melk, eieren, suiker",
        instructions = "Mixen en bakken.",
        time = 15
    ),
    Recipe(
        title = "Lasagne",
        likes = 15,
        date = "2026-10-12",
        ingredients = "Gehakt, tomaten, lasagnebladen, kaas",
        instructions = "Laagjes maken en bakken.",
        time = 45
    ),
    Recipe(
        title = "Tomatensoep",
        likes = 5,
        date = "2026-10-15",
        ingredients = "Tomaten, ui, look, bouillon",
        instructions = "Koken en mixen.",
        time = 30
    )
)

@Composable
fun RecipesList(
    onRecipeClick: (Recipe) -> Unit   // ⭐ nodig voor detailpagina
) {



        LazyColumn {
            items(dummyRecipes) { recipe ->
                RecipeCard(
                    title = recipe.title,
                    likes = recipe.likes,
                    date = recipe.date,
                    isInitiallyLiked = false,
                    onClick = { onRecipeClick(recipe) }   // ⭐ klik stuurt recept door
                )
            }
        }

}
