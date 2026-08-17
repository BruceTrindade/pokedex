package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.ligashome.HomeScreen
import com.example.listpokemons.PokedexListPokemonsViewModel
import com.example.listpokemons.PokemonListScreen
import com.example.pokemonsdetails.DetailsScreen

object Routes {
    const val GRAPH = "pokedex"
    const val HOME = "home"
    const val LIST = "list"
    const val DETAILS = "details"
}

@Composable
fun PokedexNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Routes.GRAPH) {
        navigation(startDestination = Routes.HOME, route = Routes.GRAPH) {
            composable(Routes.HOME) {
                HomeScreen(
                    onOpenPokemons = { navController.navigate(Routes.LIST) },
                )
            }

            composable(Routes.LIST) { backStackEntry ->
                val viewModel = backStackEntry.sharedListViewModel(navController)
                PokemonListScreen(
                    viewModel = viewModel,
                    onPokemonClick = { pokemon ->
                        viewModel.selectPokemon(pokemon)
                        navController.navigate(Routes.DETAILS)
                    },
                )
            }

            composable(Routes.DETAILS) { backStackEntry ->
                val viewModel = backStackEntry.sharedListViewModel(navController)
                val selected = viewModel.selectedPokemon.value
                if (selected != null) {
                    DetailsScreen(
                        pokemon = selected,
                        onBack = { navController.popBackStack() },
                    )
                }
            }
        }
    }
}

/** ViewModel scoped to the [Routes.GRAPH] entry so list and details share the same instance. */
@Composable
private fun androidx.navigation.NavBackStackEntry.sharedListViewModel(
    navController: NavHostController,
): PokedexListPokemonsViewModel {
    val parentEntry = remember(this) {
        navController.getBackStackEntry(Routes.GRAPH)
    }
    return hiltViewModel(parentEntry)
}
