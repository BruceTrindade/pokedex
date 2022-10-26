package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.PokemonRepository
import com.example.pokedex.data.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.IllegalArgumentException

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemons = MutableLiveData<List<Pokemon>>()

    val pokemons: LiveData<List<Pokemon>> = _pokemons

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        requestPokemon()
    }

    private suspend fun requestPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            val test = repository.listPokemons()
            val pokemonApiResult = repository.getPokemons()
            pokemonApiResult.enqueue()
            if (pokemonApiResult.) {
                pokemonApiResult.body()?.results.let {
                    _pokemons.postValue(
                        it!!.map { pok ->
                            val pokemonApiResult = repository.getPokemons()

                            pokemonApiResult.let { value ->
                                Pokemon(
                                    it.id,
                                    value!!.name,
                                    value.types.map { ty ->
                                        ty.type
                                    },
                                    value.id.toString()
                                )
                            }
                        }
                    )
                }
            } else {
                IllegalArgumentException("error")
            }
        }
    }
}
