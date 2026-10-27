package be.odisee.arysmauroprojectarysmauro.components.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Gebruiker(
    val naam: String,
    val voornaam: String,
    val nummer: String,
    var alias: String
)

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    var gebruiker by remember {
        mutableStateOf(
            Gebruiker(
                naam = "Arys",
                voornaam = "Mauro",
                nummer = "r1162018",
                alias = "A. Nonymous"
            )
        )
    }

    var nieuweAlias by remember { mutableStateOf(gebruiker.alias) }
    var isSaving by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(Modifier.padding(16.dp)) {
                Text("Naam: ${gebruiker.voornaam} ${gebruiker.naam}")
                Text("Studentnummer: ${gebruiker.nummer}")
                Text("Alias: ${gebruiker.alias}")
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("Alias aanpassen", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = nieuweAlias,
            onValueChange = { nieuweAlias = it },
            label = { Text("Nieuwe alias") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                // Geen coroutine meer
                gebruiker = gebruiker.copy(alias = nieuweAlias)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Opslaan")
        }
    }
}

