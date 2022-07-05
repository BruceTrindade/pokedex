package com.example.pokedex.domain

import java.util.*

data class Pokemon(
    val number: String,
    val name: String,
    val types: List<PokemonType>,
) {

    val formattedNumber = when {
        number.length < 2 -> "00$number"
        number.length < 3 -> "0$number"
        else -> number
    }

    val formattedName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png"
}


