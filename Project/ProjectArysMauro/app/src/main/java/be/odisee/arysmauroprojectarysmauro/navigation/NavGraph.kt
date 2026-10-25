package be.odisee.arysmauroprojectarysmauro.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import be.odisee.arysmauroprojectarysmauro.navigation.Screen
import be.odisee.arysmauroprojectarysmauro.components.*
import be.odisee.arysmauroprojectarysmauro.ui.screens.MyRecipeList
import be.odisee.arysmauroprojectarysmauro.viewmodel.RecipeViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    sharedViewModel: RecipeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.All.route,
        modifier = modifier
    ) {

        // TAB 0 — Alle recepten
        composable(Screen.All.route) {
            RecipesList(
                modifier = Modifier,
                recipes = sharedViewModel.sortedRecipes(),
                onRecipeClick = { recipe ->
                    navController.navigate("${Screen.Details.route}/${recipe.id}")
                },
                onLikeToggle = { recipe ->
                    sharedViewModel.toggleLike(recipe)
                }
            )
        }

        // TAB 1 — Mijn recepten
        composable(Screen.My.route) {
            MyRecipeList(
                modifier = Modifier,
                recipes = sharedViewModel.recipes.filter {
                    it.gebruikerAangemaaktAlias == "Mauro"
                },
                onRecipeClick = { recipe ->
                    navController.navigate("${Screen.Details.route}/${recipe.id}")
                },
                onLikeToggle = { recipe ->
                    sharedViewModel.toggleLike(recipe)
                },
                onDelete = { recipe ->
                    sharedViewModel.deleteRecipe(recipe)
                },
                huidigeGebruiker = "Mauro"
            )
        }

        // TAB 2 — Profiel
        composable(Screen.Profile.route) {
            ProfileScreen()
        }

        // Add
        composable(Screen.Add.route) {
            AddRecipeScreen(onBack = { navController.popBackStack() })
        }

        // Details
        composable("${Screen.Details.route}/{id}") { backStackEntry ->
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
    }
}
