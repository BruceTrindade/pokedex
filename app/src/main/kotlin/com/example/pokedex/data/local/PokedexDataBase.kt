package com.example.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedex.data.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
@TypeConverters(PokedexConverters::class)
abstract class PokedexDataBase : RoomDatabase() {
    abstract fun pokedexDao(): PokedexDao
}