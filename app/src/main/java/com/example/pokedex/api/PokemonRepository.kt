package com.example.pokedex.api

import android.util.Log
import com.example.pokedex.model.PokemonAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {

    private val service: PokemonService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(PokemonService::class.java)
    }

    fun getPokemons() {
        val call = service.listPokemons()

        call.enqueue(object : Callback<PokemonAPI> {
            override fun onResponse(call: Call<PokemonAPI>,
                                    response: Response<PokemonAPI>) {
               Log.d("Pokemon_API", "Pokemons Success")
            }

            override fun onFailure(call: Call<PokemonAPI>,
                                   t: Throwable) {
              Log.e("Pokemon_API", "Error loading pokemon list", t)
            }

        })
    }

}