package com.example.pokedex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.viewmodel.PokemonViewModel
import com.example.pokedex.viewmodel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {

    val recyclerView by lazy { findViewById<RecyclerView>(R.id.pokemon_recycler) }

    val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.pokemons.observe(this, Observer { listPokemon ->
            loadRecyclerView(listPokemon)
        })

    }

    private fun loadRecyclerView(pokemons: List<Pokemon>) {
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = PokemonAdapter(pokemons)

    }
}