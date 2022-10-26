package com.example.pokedex.api

import com.example.pokedex.data.Pokemon
import com.example.pokedex.data.model.PokemonAPI
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun listPokemons(
        @Query("limit") limit: Int
    ): Response<PokemonAPI>

    @GET("pokemon.json")
    suspend fun getPokemon(): Call<List<Pokemon>>
}