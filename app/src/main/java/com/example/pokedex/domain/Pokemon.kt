package com.example.pokedex.domain

data class Pokemon(
    val imageUrl: String,
    val number: String,
    val name: String,
    val types: List<PokemonType>,
)


