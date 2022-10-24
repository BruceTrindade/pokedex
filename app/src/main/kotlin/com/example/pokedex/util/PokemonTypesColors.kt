package com.example.pokedex.util

import com.example.pokedex.R

object PokemonTypesColors {
    fun getTypeColor(type: String): Int {
        return when (type) {
          "fighting" -> R.color.fighting
          "flying" -> R.color.flying
          "poison" -> R.color.poison
          "ground" -> R.color.ground
           "rock" -> R.color.rock
           "bug" -> R.color.bug
           "ghost" -> R.color.ghost
           "steel" -> R.color.steel
           "fire" -> R.color.fire
           "water" -> R.color.water
           "grass" -> R.color.grass
           "electric" -> R.color.electric
           "psychic" -> R.color.psychic
           "ice" -> R.color.ice
           "dragon" -> R.color.dragon
           "fairy" -> R.color.fairy
           "dark" -> R.color.dark
           else -> R.color.white
        }
    }
}