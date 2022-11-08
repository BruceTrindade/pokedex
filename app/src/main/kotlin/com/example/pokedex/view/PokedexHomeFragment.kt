package com.example.pokedex.view

import android.os.Bundle
import android.view.View
import androidx.compose.material3.MaterialTheme
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dsmpokedex.PokeballLoading
import com.example.pokedex.R
import com.example.pokedex.util.Resource
import com.example.pokedex.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pokedex_home.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokedexHomeFragment : Fragment(R.layout.fragment_pokedex_home) {

    private lateinit var viewModel: PokemonViewModel

    private val pokemonAdapter by lazy { PokemonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pokemonsObserver()
        setupListPokemons()
        setupClickPokemons()
    }

    private fun pokemonsObserver() = viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.pokemons.collect { resources ->
                when (resources) {
                    is Resource.Success -> {
                        pokeball_loading.visibility = View.GONE
                        text_pokedex.visibility = View.GONE
                        resources.data?.let {
                            pokemonAdapter.pokemons = it
                        }
                    }
                    is Resource.Error -> {
                        throw IllegalArgumentException(
                            "Error: resource error"
                        )
                    }
                    is Resource.Loading -> {
                        pokeball_loading.visibility = View.VISIBLE
                        text_pokedex.visibility = View.VISIBLE
                        setupLoadingCompose()
                    }
                    is Resource.Empty -> {
                        throw IllegalArgumentException(
                            "Error: empty"
                        )
                    }
                }
            }
        }
    }

    private fun setupLoadingCompose() {
        pokeball_loading.setContent {
            MaterialTheme {
                PokeballLoading()
            }
        }
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
