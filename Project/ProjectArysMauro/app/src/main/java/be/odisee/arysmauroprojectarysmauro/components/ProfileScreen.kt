package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

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
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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

        Spacer(Modifier.height(32.dp))

        Text("Alias aanpassen", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = nieuweAlias,
            onValueChange = { nieuweAlias = it },
            label = { Text("Nieuwe alias") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                scope.launch {
                    isSaving = true
                    kotlinx.coroutines.delay(800)
                    gebruiker = gebruiker.copy(alias = nieuweAlias)
                    isSaving = false
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isSaving
        ) {
            Text(if (isSaving) "Opslaan..." else "Opslaan")
        }
    }
}

