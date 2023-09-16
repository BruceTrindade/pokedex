package com.example.core

import androidx.navigation.NavController
import com.example.core.data.Pokemon
import com.example.core.data.PokemonType
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
