package com.example.pokedex.data

import com.example.pokedex.api.PokemonService
import com.example.pokedex.data.local.PokedexDao
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val service: PokemonService,
    private val dao: PokedexDao
) {

    suspend fun listPokemons(limit: Int = 100) = service.listPokemons(limit)
    suspend fun getPokemons(number: Int) = service.getPokemon(number)

    suspend fun insert(pokemonModel: Pokemon) = dao.insert(pokemonModel)
    fun getAll() = dao.getAll()
    suspend fun delete(pokemonModel: Pokemon) = dao.delete(pokemonModel)
}
