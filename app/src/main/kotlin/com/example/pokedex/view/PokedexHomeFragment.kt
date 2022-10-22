package com.example.pokedex.view

import android.os.Bundle
import android.view.View
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dsmpokedex.PokeCardView
import com.example.pokedex.R
import com.example.pokedex.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pokedex_home.*
import kotlinx.android.synthetic.main.pokemon_row.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokedexHomeFragment : Fragment(R.layout.fragment_pokedex_home) {

    private lateinit var viewModel: PokemonViewModel

    private val pokemonAdapter by lazy { PokemonAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        pokemonsObserver()
        setupListPokemons()
        setupClickPokemons()

        // PokeCardView(Context)
    }

    private fun pokemonsObserver() = lifecycleScope.launch {
        viewModel.pokemons.observe(
            viewLifecycleOwner,
            Observer {
                pokemonAdapter.pokemons = it
            }
        )
    }

    private fun setupListPokemons() {
        pokemon_recycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = pokemonAdapter
        }
    }

    private fun setupClickPokemons() {
        pokemonAdapter.setOnClickListener { pokemon ->
            val actions = PokedexHomeFragmentDirections.actionFirstDestinationToPokedexDetailsFragment(pokemon)
            findNavController().navigate(actions)
        }
    }
}
