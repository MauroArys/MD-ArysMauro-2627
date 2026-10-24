package be.odisee.arysmauroprojectarysmauro.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import be.odisee.arysmauroprojectarysmauro.components.*
import be.odisee.arysmauroprojectarysmauro.data.dummyRecipes

@Composable
fun NavGraph(
    navController: NavHostController,
    selectedTab: Int,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "tabs",
        modifier = modifier
    ) {

        // Tabs-scherm
        composable("tabs") {
            HomeScreen(
                navController = navController,
                selectedTab = selectedTab
            )
        }

        // Details-scherm
        composable("details/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt()
            val recipe = dummyRecipes.first { it.id == id }
            RecipeDetailScreen(recipe, onBack = { navController.popBackStack() })
        }

        // Add-scherm
        composable("add") {
            AddRecipeScreen(onBack = { navController.popBackStack() })
        }
    }
}
