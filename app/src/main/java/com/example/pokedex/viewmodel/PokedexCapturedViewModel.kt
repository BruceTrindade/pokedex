package com.example.pokedex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.Pokemon
import com.example.pokedex.data.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexCapturedViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _captureds = MutableLiveData<List<Pokemon>>()
    val captureds: LiveData<List<Pokemon>> = _captureds

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        repository.getAll().value.let {
            _captureds.postValue(
                it!!.map { pok ->
                    pok.number
                    pok.name
                    pok.types
                    pok.imageUrl
                    Pokemon(
                        pok.number,
                        pok.name,
                        pok.types,
                        pok.imageUrl
                    )
                }
            )
        }

    }

    fun delete(pokemonModel: Pokemon) = viewModelScope.launch {
        repository.delete(pokemonModel)
    }
}
