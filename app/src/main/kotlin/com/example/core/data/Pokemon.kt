package com.example.core.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Pokemon(
    val number: String,
    val name: String,
    val types: @RawValue List<PokemonType>,
    val imageUrl: String
) : Parcelable
