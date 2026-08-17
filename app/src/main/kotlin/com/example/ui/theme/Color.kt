package com.example.ui.theme

import androidx.compose.ui.graphics.Color

// Base palette
val PokeWhite = Color(0xFFFFFAF0)
val PokeBlack = Color(0xFF3E4650)
val Background = Color(0xFF2B292B)

// Pokémon type colors (migrated from colors.xml)
val TypeBugGrass = Color(0xFF2CDAB1)
val TypeWaterFightingNormal = Color(0xFF58ABF6)
val TypePoisonGhost = Color(0xFF9F5BBA)
val TypeFlying = Color(0xFF90B1C5)
val TypeRockGround = Color(0xFFCA8179)
val TypeSteel = Color(0xFF5C756D)
val TypeFire = Color(0xFFF7786B)
val TypeElectricPsychic = Color(0xFFE0E64B)
val TypeIce = Color(0xFF7ECFF2)
val TypeDragon = Color(0xFF378A94)
val TypeFairy = Color(0xFF9E1A44)
val TypeDark = Color(0xFF303943)

/**
 * Maps a Pokémon type name to its brand color. Case-insensitive so it works with
 * both the API's lowercase names and the display (Title case) names.
 */
fun pokemonTypeColor(type: String): Color = when (type.lowercase()) {
    "grass", "bug" -> TypeBugGrass
    "water", "fighting", "normal" -> TypeWaterFightingNormal
    "poison", "ghost" -> TypePoisonGhost
    "flying" -> TypeFlying
    "ground", "rock" -> TypeRockGround
    "steel" -> TypeSteel
    "fire" -> TypeFire
    "electric", "psychic" -> TypeElectricPsychic
    "ice" -> TypeIce
    "dragon" -> TypeDragon
    "fairy" -> TypeFairy
    "dark" -> TypeDark
    else -> TypeDragon
}
