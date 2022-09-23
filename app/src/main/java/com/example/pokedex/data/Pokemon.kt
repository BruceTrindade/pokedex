package com.example.pokedex.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

@Parcelize
data class Pokemon(
    val number: String,
    val name: String,
    val types: @RawValue List<PokemonType>,
) : Parcelable {

    val formattedNumber = when {
        number.length < 2 -> "00$number"
        number.length < 3 -> "0$number"
        else -> number
    }

    val formattedName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png"
}


