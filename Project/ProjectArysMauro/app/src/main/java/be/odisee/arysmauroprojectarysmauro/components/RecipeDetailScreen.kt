package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.odisee.arysmauroprojectarysmauro.data.Recipe

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // Titel
        Text(
            text = recipe.titel,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 28.sp
        )

        Spacer(Modifier.height(16.dp))

        // Info blok
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Door: ${recipe.gebruikerAangemaaktAlias}")
                Text("Likes: ${recipe.likes}")
                Text("Tijd: ${recipe.tijd} minuten")
                Text("Datum: ${recipe.datumAangemaakt}")
            }
        }

        Spacer(Modifier.height(24.dp))

        // Ingrediënten
        Text(
            "Ingrediënten",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))
        Text(recipe.ingredienten.replace("\\n", "\n"))

        Spacer(Modifier.height(24.dp))

        // Stappen
        Text(
            "Bereiding",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(8.dp))
        Text(recipe.stappen.replace("\\n", "\n"))

        Spacer(Modifier.height(32.dp))

        // Terugknop
        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Terug")
        }
    }
}




