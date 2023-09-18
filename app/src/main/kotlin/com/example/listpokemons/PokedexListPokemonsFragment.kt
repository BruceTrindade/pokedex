package com.example.listpokemons

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
import com.example.core.R
import com.example.core.databinding.FragmentPokedexListPokemonsBinding
import com.example.dsmpokedex.PokeballLoading
import com.example.core.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokedexListPokemonsFragment : Fragment(R.layout.fragment_pokedex_list_pokemons) {

    private lateinit var viewModel: PokedexListPokemonsViewModel

    private var _binding: FragmentPokedexListPokemonsBinding? = null
    private val binding get() = _binding!!

    private val pokemonAdapter by lazy { PokemonAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokedexListPokemonsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentPokedexListPokemonsBinding.bind(view)
        pokemonsObserver()
        setupListPokemons()
        setupClickPokemons()
    }

    private fun pokemonsObserver() = viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.pokemons.collect { resources ->
                when (resources) {
                    is Resource.Success -> {
                        binding.pokeballLoading.visibility = View.GONE
                        binding.textPokedex.visibility = View.GONE
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
                        binding.pokeballLoading.visibility = View.VISIBLE
                        binding.textPokedex.visibility = View.VISIBLE
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
        binding.pokeballLoading.setContent {
            MaterialTheme {
                PokeballLoading()
            }
        }
    }
    private fun setupListPokemons() {
        binding.pokemonRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = pokemonAdapter
        }
    }

    private fun setupClickPokemons() {
        pokemonAdapter.setOnClickListener { pokemon ->
            val actions = PokedexListPokemonsFragmentDirections.actionPokedexListPokemonsFragmentToPokedexDetailsFragment(pokemon)
            findNavController().navigate(actions)
        }
    }
}
