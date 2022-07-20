package com.example.pokedex.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.viewmodel.PokemonViewModel
import com.example.pokedex.viewmodel.PokemonViewModelFactory
import kotlinx.android.synthetic.main.fragment_pokedex_home.*

class PokedexHomeFragment : Fragment(R.layout.fragment_pokedex_home) {

        val viewModel by lazy {
            ViewModelProvider(this, PokemonViewModelFactory())
                .get(PokemonViewModel::class.java)
        }

   private lateinit var pokemonAdapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.pokemons.observe(viewLifecycleOwner, Observer { listPokemon ->
            loadRecyclerView(listPokemon)
        })
    }

    private fun loadRecyclerView(pokemons: List<Pokemon>) {
        this.pokemonAdapter = PokemonAdapter(pokemons) { pokemon ->
            val actions = PokedexHomeFragmentDirections.actionFirstDestinationToPokedexDetailsFragment(pokemon)
            findNavController().navigate(actions)
        }
        pokemon_recycler.layoutManager = GridLayoutManager(context,2)
        pokemon_recycler.adapter = this.pokemonAdapter

    }
}

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_pokedex_home, container, false)
//    }

