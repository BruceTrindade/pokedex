package com.example.pokedex.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokedex.data.local.PokedexConverters

@Entity(tableName = "pokemon")
@TypeConverters(PokedexConverters::class)
data class Pokemon(
    @PrimaryKey
    val number: String,
    val name: String,
    val types: List<String>? = null,
    val imageUrl: String
)
