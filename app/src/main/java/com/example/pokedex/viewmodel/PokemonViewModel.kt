package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon
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
            if (test.isSuccessful) {
                test.body()?.results.let {
                    _pokemons.postValue(
                        it!!.map { pok ->
                            val number = pok.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                                .replace("/", "").toString()
                            val pokemonApiResult = repository.getPokemons(number.toInt())

                            pokemonApiResult.body().let { value ->
                                Pokemon(
                                    value?.id.toString(),
                                    value!!.name,
                                    value.types.map { ty ->
                                        ty.type
                                    }
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
