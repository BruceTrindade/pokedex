package com.example.pokedex.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {

    private val service: PokemonService

    suspend fun listPokemons(limit: Int = 100) = service.listPokemons(limit)

//    suspend fun listPokemons(limit: Int = 100): PokemonAPI? {
//        val call = service.listPokemons(limit)
//
//        return call.execute().body()
//    }

    suspend fun getPokemons(number: Int) = service.getPokemon(number)

//    fun getPokemons(number: Int): PokemonApiResult? {
//        val call = service.getPokemon(number)
//
//        return call.execute().body()
//    }
}
