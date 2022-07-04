package com.example.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.api.PokemonRepository
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.pokemon_recycler)
        val layoutManager = LinearLayoutManager(this)

        val pokemon = Pokemon(
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png",
            "004",
            "charmander",
            listOf(
                PokemonType("Fire")
            )
        )
        val pokemons = listOf(
            pokemon,
            pokemon,
            pokemon,
            pokemon,
            pokemon,
            pokemon,
            pokemon,
            pokemon,
            pokemon,
        )

        val pokemonsApi = PokemonRepository.getPokemons()

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}