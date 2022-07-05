package com.example.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon

class PokemonViewModel: ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon>>()

    init {
        Thread(Runnable {
            loadPokemons()
        }).start()
    }

    private fun loadPokemons(){
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {
            pokemons.postValue(it.map {  pokemon ->
                val number = pokemon.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toString()
                val pokemonApiResult = PokemonRepository.getPokemons(number.toInt())

                pokemonApiResult.let {
                    Pokemon(
                        pokemonApiResult?.id.toString(),
                        pokemonApiResult!!.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        }
                    )
                }
            })
        }
    }

}