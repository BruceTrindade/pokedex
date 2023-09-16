package com.example.core.api

import com.example.core.data.model.PokemonAPI
import com.example.core.data.model.PokemonApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    suspend fun listPokemons(
        @Query("limit") limit: Int
    ): Response<PokemonAPI>

    @GET("pokemon/{number}")
    suspend fun getPokemon(
        @Path("number") number: Int
    ): Response<PokemonApiResult>
}
