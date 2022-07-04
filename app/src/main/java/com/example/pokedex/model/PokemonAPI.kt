package com.example.pokedex.model

import com.example.pokedex.domain.PokemonType

data class PokemonAPI(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>,
)

data class PokemonResult (
    val name: String,
    val url: String,
)

data class PokemonApiResult (
    val id: Int,
    val types: PokemonTypeSlot,
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType,
)
