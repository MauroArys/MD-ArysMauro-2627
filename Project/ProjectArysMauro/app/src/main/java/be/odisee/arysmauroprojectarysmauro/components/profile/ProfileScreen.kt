package be.odisee.arysmauroprojectarysmauro.components.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeUiState
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel,
    uiState: RecipeUiState
) {

    var nieuweAlias by remember(uiState.huidigeAlias) {
        mutableStateOf(uiState.huidigeAlias)
    }

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
                Text("Naam: Mauro Arys")
                Text("Studentnummer: r1162018")
                Text("Alias: ${uiState.huidigeAlias}")
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
                isSaving = true
                viewModel.updateAlias(nieuweAlias)
                isSaving = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isSaving) "Opslaan..." else "Opslaan")
        }
    }
}




