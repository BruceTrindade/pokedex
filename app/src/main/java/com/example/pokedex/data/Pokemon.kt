package com.example.pokedex.data

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue
import java.io.Serializable
import java.util.*

data class Pokemon(
    @SerializedName("number")
    val number: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("types")
    val types: @RawValue List<PokemonType>,
) : Serializable {

    val formattedNumber = when {
        number.length < 2 -> "00$number"
        number.length < 3 -> "0$number"
        else -> number
    }

    val formattedName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png"
}


