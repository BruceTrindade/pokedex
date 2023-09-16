package com.example.core.data

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Pokemon(
    @PrimaryKey
    val number: String,
    val name: String,
    val types: @RawValue List<PokemonType>,
    val imageUrl: String
) : Parcelable
