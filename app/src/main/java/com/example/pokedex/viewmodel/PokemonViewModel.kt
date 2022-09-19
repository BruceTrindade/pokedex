package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
)  : ViewModel() {

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
      //  loadPokemons()
    }

    private fun requestPokemon() {

        viewModelScope.launch(Dispatchers.IO) {
            val test = repository.listPokemons()
            _pokemons.po
            test.execute().body().results.let {

            }
            }
            repository.listPokemons()
        }
    }

  /*  private suspend fun loadPokemons() {
        val pokemonsApiResult = repository.listPokemons()
        if (pokemonsApiResult.isExecuted)
            pokemonsApiResult.let {
                _pokemons.value = pokemonsApiResult.
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
    }*/
}
