package com.example.listpokemons

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.R
import com.example.core.databinding.FragmentPokedexListPokemonsBinding
import com.example.core.utils.Resource
import com.example.dsmpokedex.PokeballLoading
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
                        setupComposeViews()
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

    @OptIn(ExperimentalMaterial3Api::class)
    private fun setupComposeViews() {
        binding.pokeballBar.setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        topBar()
                    },
                    content = { innerPadding ->
                        Column(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
                            binding.pokeballLoading.setContent {
                                MaterialTheme {
                                    PokeballLoading()
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun topBar() {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "Centered TopAppBar",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Localized description"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* doSomething() */ }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Localized description"
                    )
                }
            }
        )
    }

    private fun setupListPokemons() {
        binding.pokemonRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = pokemonAdapter
        }
    }

    private fun setupClickPokemons() {
        pokemonAdapter.setOnClickListener { pokemon ->
            val actions =
                PokedexListPokemonsFragmentDirections.actionPokedexListPokemonsFragmentToPokedexDetailsFragment(
                    pokemon
                )
            findNavController().navigate(actions)
        }
    }
}
