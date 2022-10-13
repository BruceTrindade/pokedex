package com.example.pokedex.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.R
import com.example.pokedex.viewmodel.PokedexCapturedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pokedex_captured.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokedexCapturedFragment : Fragment(R.layout.fragment_pokedex_captured) {

    private lateinit var viewModel: PokedexCapturedViewModel

    private val pokemonAdapter by lazy { PokemonAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokedexCapturedViewModel::class.java)
        pokemonsObserver()
        setupRecyclerView()

    }

    private fun pokemonsObserver() = lifecycleScope.launch {
        viewModel.captureds.observe(
            viewLifecycleOwner,
            Observer {
                pokemonAdapter.pokemons = it
            }
        )
    }

    private fun setupRecyclerView() {
        captured_recycler.apply {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}
