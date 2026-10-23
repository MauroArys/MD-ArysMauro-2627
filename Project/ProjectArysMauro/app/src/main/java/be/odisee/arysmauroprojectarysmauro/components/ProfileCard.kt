package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.odisee.arysmauroprojectarysmauro.ui.theme.ProjectArysMauroTheme

@Composable
fun ProfileCardDummy() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Naam: Mauro Arys",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Alias: MauroDev",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Nieuwe alias") }
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = { /* nog niets */ }) {
            Text("Opslaan")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardDummyPreview() {
    ProjectArysMauroTheme() {
        ProfileCardDummy()
    }
}