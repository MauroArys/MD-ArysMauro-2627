package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddRecipeScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    var titel by remember { mutableStateOf("") }
    var tijd by remember { mutableStateOf("") }
    var ingredienten by remember { mutableStateOf("") }
    var stappen by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Nieuw recept toevoegen", style = MaterialTheme.typography.headlineMedium, fontSize = 26.sp)
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = titel,
            onValueChange = { titel = it },
            label = { Text("Titel") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = tijd,
            onValueChange = { tijd = it },
            label = { Text("Tijd (minuten)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = ingredienten,
            onValueChange = { ingredienten = it },
            label = { Text("Ingrediënten") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = stappen,
            onValueChange = { stappen = it },
            label = { Text("Bereiding") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 6
        )

        Spacer(Modifier.height(24.dp))

        // Opslaan + Terug naast elkaar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    // later: API-call
                    println("Nieuw recept:")
                    println("Titel: $titel")
                    println("Tijd: $tijd")
                    println("Ingrediënten: $ingredienten")
                    println("Stappen: $stappen")

                    onBack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Opslaan")
            }

            Spacer(Modifier.width(16.dp))

            Button(
                onClick = onBack,
                modifier = Modifier.weight(1f)

            ) {
                Text("Terug")
            }
        }
    }
}
