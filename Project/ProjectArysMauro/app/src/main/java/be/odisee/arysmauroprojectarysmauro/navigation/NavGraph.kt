package be.odisee.arysmauroprojectarysmauro.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import be.odisee.arysmauroprojectarysmauro.components.*
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    selectedTab: Int,
    onSelectTab: (Int) -> Unit,
    sharedViewModel: RecipeViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "tabs",
        modifier = modifier
    ) {

        composable("tabs") {
            HomeScreen(
                navController = navController,
                selectedTab = selectedTab,
                modifier = modifier,
                viewModel = sharedViewModel
            )
        }

        composable("details/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
            val recipe = sharedViewModel.getRecipeById(id)

            if (recipe == null) {
                navController.popBackStack()
                return@composable
            }

            RecipeDetailScreen(
                recipe = recipe,
                viewModel = sharedViewModel,
                huidigeGebruiker = "Mauro",
                onBack = { navController.popBackStack() },
                modifier = Modifier
            )

        }



        composable("add") {
            AddRecipeScreen(onBack = { navController.popBackStack() })
        }
    }
}
