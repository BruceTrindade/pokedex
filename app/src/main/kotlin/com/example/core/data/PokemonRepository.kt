package com.example.core.data

import com.example.core.api.PokemonService
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val service: PokemonService
) {

    suspend fun listPokemons(limit: Int = 900) = service.listPokemons(limit)
    suspend fun getPokemons(number: Int) = service.getPokemon(number)
}
