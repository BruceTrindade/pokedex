package com.example.pokedex.util

import com.example.pokedex.R

object PokemonTypesColors {
    fun getTypeColor(type: String): Int {
        return when (type) {
            "grass", "bug" -> R.color.bug_grass
            "water", "fighting", "normal" -> R.color.water_fighting_normal
            "poison", "ghost" -> R.color.poison_ghost
            "flying" -> R.color.flying
            "ground", "rock" -> R.color.rock_ground
            "steel" -> R.color.steel
            "fire" -> R.color.fire
            "electric, psychic" -> R.color.electric_psychic
            "ice" -> R.color.ice
            "dragon" -> R.color.dragon
            "fairy" -> R.color.fairy
            "dark" -> R.color.dark
            else -> R.color.dragon
        }
    }
}
