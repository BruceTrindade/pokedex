package com.example.pokedex.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokedex.data.local.PokedexConverters
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity(tableName = "pokemon")
@TypeConverters(PokedexConverters::class)
data class Pokemon(
    @PrimaryKey
    val number: String,
    val name: String,
    val types: @RawValue List<PokemonType>,
    val imageUrl: String
) : Parcelable {

//    val formattedNumber = when {
//        number.length < 2 -> "00$number"
//        number.length < 3 -> "0$number"
//        else -> number
//    }
//
//    val formattedName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
//
//    val imageUrl = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png"
}

/*
@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("number")
    val number: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("types")
    val types: @RawValue List<PokemonType>
) : Serializable {

 */
