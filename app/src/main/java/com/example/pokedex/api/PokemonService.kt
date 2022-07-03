package com.example.pokedex.api

import com.example.pokedex.model.PokemonAPI
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    fun listPokemons(@Query("limit") limit: Int): Call<PokemonAPI>

    @GET("pokemon/{number}")
    fun getPokemon(number: Int): Call<PokemonAPI>
}