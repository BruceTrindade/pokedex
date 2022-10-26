package com.example.pokedex.util

import java.util.*

fun String.formatteNumber() = when {
    this.length < 2 -> "00$this"
    this.length < 3 -> "0$this"
    else -> this
}

fun String.formatteName() = this.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}

fun String.formattedImageLink() = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/${this.formatteNumber()}.png"