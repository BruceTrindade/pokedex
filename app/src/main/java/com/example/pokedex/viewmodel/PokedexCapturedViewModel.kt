package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.Pokemon
import com.example.pokedex.data.PokemonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokedexCapturedViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _captureds = MutableLiveData<List<Pokemon>>()
    val captureds: LiveData<List<Pokemon>> = _captureds

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
//        repository.getAll(). {
//
//        }

       // requestPokemon()
    }

    fun delete(pokemonModel: Pokemon) = viewModelScope.launch {
        repository.delete(pokemonModel)
    }

}