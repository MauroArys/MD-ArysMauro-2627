package be.odisee.arysmauroprojectarysmauro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import be.odisee.arysmauroprojectarysmauro.components.BottomBar
import be.odisee.arysmauroprojectarysmauro.components.TopBar
import be.odisee.arysmauroprojectarysmauro.components.HomeScreen
import be.odisee.arysmauroprojectarysmauro.navgraph.NavGraph
import be.odisee.arysmauroprojectarysmauro.ui.theme.ProjectArysMauroTheme
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProjectArysMauroTheme {

                val navController = rememberNavController()
                var selectedTab by remember { mutableIntStateOf(0) }
                val recipeViewModel: RecipeViewModel = viewModel()

                Scaffold(
                    topBar = { TopBar(selectedTab) },
                    bottomBar = {
                        BottomBar(
                            selectedTab = selectedTab,
                            onTabSelected = { selectedTab = it }
                        )
                    }
                ) { innerPadding ->

                    NavGraph(
                        navController = navController,
                        selectedTab = selectedTab,
                        onSelectTab = { selectedTab = it },
                        modifier = Modifier.padding(innerPadding),
                        sharedViewModel = recipeViewModel
                    )

                }
            }
        }
    }
}
