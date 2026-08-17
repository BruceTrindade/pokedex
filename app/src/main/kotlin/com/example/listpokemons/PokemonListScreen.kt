package com.example.listpokemons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.data.Pokemon
import com.example.core.utils.Resource
import com.example.dsmpokedex.PokeballLoading
import com.example.ui.components.PokemonCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    viewModel: PokedexListPokemonsViewModel,
    onPokemonClick: (Pokemon) -> Unit,
) {
    val state by viewModel.pokemons.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Pokédex", maxLines = 1, overflow = TextOverflow.Ellipsis)
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Favorite, contentDescription = "Favoritos")
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            when (val resource = state) {
                is Resource.Loading -> LoadingState()
                is Resource.Success -> PokemonGrid(
                    pokemons = resource.data.orEmpty(),
                    onPokemonClick = onPokemonClick,
                )

                is Resource.Error -> Text(
                    text = resource.message ?: "Ocorreu um erro",
                )

                is Resource.Empty -> Text(text = "Nenhum pokémon encontrado")
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        PokeballLoading()
        Text(
            text = "Calling the pokedex center...",
            modifier = Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
private fun PokemonGrid(
    pokemons: List<Pokemon>,
    onPokemonClick: (Pokemon) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(pokemons, key = { it.number }) { pokemon ->
            PokemonCard(pokemon = pokemon, onClick = onPokemonClick)
        }
    }
}
