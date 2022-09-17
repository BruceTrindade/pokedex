package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon
import kotlinx.coroutines.launch
import java.io.IOException

class PokemonViewModel : ViewModel(
    private val repository: PokemonRepository
) {

    private val _pokemons = MutableLiveData<List<Pokemon>>()

    val pokemons: LiveData<List<Pokemon>> = _pokemons

//    init {
//        Thread(
//            Runnable {
//                loadPokemons()
//            }
//        ).start()
//    }

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch() {
        loadPokemons()
    }

    private suspend fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.listPokemons()

        pokemonsApiResult?.results?.let {
            _pokemons.postValue(
                it.map { pokemon ->
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
                }
            )
        }
    }
}
