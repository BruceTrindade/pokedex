package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.Pokemon
import com.example.pokedex.data.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.IllegalArgumentException

@HiltViewModel
class PokedexDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _detailsPokemons = MutableLiveData<List<Pokemon>>()

    val detailsPokemons: LiveData<List<Pokemon>> = _detailsPokemons

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
      //  requestPokemon()
    }

    private suspend fun requestPokemon() {
        viewModelScope.launch(Dispatchers.IO) {
            val test = repository.listPokemons()
            if (test.isSuccessful) {
                test.body()?.results.let {
                    _detailsPokemons.postValue(
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

    fun insert(characterModel: Pokemon) = viewModelScope.launch {
        repository.insert(characterModel)
    }
}
