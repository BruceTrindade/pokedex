package com.example.listpokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.Pokemon
import com.example.core.data.PokemonRepository
import com.example.core.data.model.PokemonAPI
import com.example.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PokedexListPokemonsViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemons = MutableStateFlow<Resource<List<Pokemon>>>(Resource.Loading())
    val pokemons: StateFlow<Resource<List<Pokemon>>> = _pokemons.asStateFlow()

    /** Pokémon chosen on the list, read by the details screen (shared via nav graph scope). */
    private val _selectedPokemon = MutableStateFlow<Pokemon?>(null)
    val selectedPokemon: StateFlow<Pokemon?> = _selectedPokemon.asStateFlow()

    fun selectPokemon(pokemon: Pokemon) {
        _selectedPokemon.value = pokemon
    }

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        requestPokemon()
    }

    private suspend fun requestPokemon() {
        try {
            val response = repository.listPokemons()
            _pokemons.value = handleResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _pokemons.value = Resource.Error("Erro de conexão com a internet")
                else -> _pokemons.value = Resource.Error("Falha na conversaão de dados")
            }
        }
    }

    private suspend fun handleResponse(response: Response<PokemonAPI>): Resource<List<Pokemon>> {
        if (response.isSuccessful) {
            response.body()?.results.let { values ->
                val value = values!!.map { pok ->
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
                return Resource.Success(value)
            }
        }
        return Resource.Error(response.message())
    }
}
