package com.example.pokedex

import androidx.navigation.NavController
import com.example.pokedex.data.Pokemon
import com.example.pokedex.data.PokemonType
import org.mockito.Mockito

object MockPokemon {

    val navController = Mockito.mock(NavController::class.java)

    val list: List<PokemonType> = listOf(PokemonType("grass"), PokemonType("poison"))

    val mockPokemon: Pokemon = Pokemon(
        number = "3",
        name = "venusaur",
        types = list,
        imageUrl = "3"
    )
}
