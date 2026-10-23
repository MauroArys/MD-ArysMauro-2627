package be.odisee.arysmauroprojectarysmauro.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import be.odisee.arysmauroprojectarysmauro.data.Recipe
import be.odisee.arysmauroprojectarysmauro.ui.theme.ProjectArysMauroTheme

@Composable
fun HomeScreen() {
    var selectedTab = remember { mutableStateOf(0) }
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }


    if (selectedRecipe != null) {
        RecipeDetailScreen(
            recipe = selectedRecipe!!,
            onBack = { selectedRecipe = null }
        )
        return
    }

    Scaffold(
        topBar = { TopBar(selectedTab.value) },
        bottomBar = {
            BottomBar(
                selectedTab = selectedTab.value,
                onTabSelected = { selectedTab.value = it }
            )
        }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab.value) {
                0 -> RecipesList(onRecipeClick = { selectedRecipe = it })
                1 -> MyRecipesList(onRecipeClick = { selectedRecipe = it })
                2 -> ProfileCardDummy()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ProjectArysMauroTheme {
        HomeScreen()
    }
}
