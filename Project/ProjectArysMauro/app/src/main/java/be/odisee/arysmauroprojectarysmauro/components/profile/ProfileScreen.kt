package be.odisee.arysmauroprojectarysmauro.components.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.R
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeUiState
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel,
    uiState: RecipeUiState
) {

    //nieuwe alias bijhouden. Het veld past automatisch aan!
    var nieuweAlias by remember(uiState.huidigeAlias) {
        mutableStateOf(uiState.huidigeAlias)
    }

    var nieuweErvaring by remember(uiState.ervaring) {
        mutableStateOf(uiState.ervaring)
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
                Text(stringResource(R.string.profile_name_label, "Mauro Arys"))
                Text(stringResource(R.string.profile_student_number_label, "r1162018"))
                Text(stringResource(R.string.profile_alias_label, uiState.huidigeAlias))
                Text(stringResource(R.string.profile_requestCount, uiState.requestCount))
                Text(stringResource(R.string.profile_ervaring, uiState.ervaring))
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(stringResource(R.string.profile_edit_alias), style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = nieuweAlias,
            onValueChange = { nieuweAlias = it },
            label = { Text(stringResource(R.string.profile_new_alias)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Text(stringResource(R.string.profile_new), style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = nieuweErvaring.toString(),
            onValueChange = { nieuweErvaring = it.toIntOrNull() ?: 0},
            isError = nieuweErvaring !in 0..99,
            label = { Text(stringResource(R.string.profile_new_ervaring)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))


        //opslaan knop
        Button(
            onClick = {
                isSaving = true
                viewModel.updateAlias(nieuweAlias, nieuweErvaring)
                isSaving = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isSaving) stringResource(R.string.profile_saving) else stringResource(R.string.profile_save))
        }
    }
}




