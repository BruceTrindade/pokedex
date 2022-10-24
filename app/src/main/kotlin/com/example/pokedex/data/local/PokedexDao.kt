package com.example.pokedex.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pokedex.data.Pokemon

@Dao
interface PokedexDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon): Long

    @Query("SELECT * FROM pokemon ORDER BY number")
    fun getAll(): LiveData<List<Pokemon>>

    @Delete
    suspend fun delete(characterModel: Pokemon)
}
