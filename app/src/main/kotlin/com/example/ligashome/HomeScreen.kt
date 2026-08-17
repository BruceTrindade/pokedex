package com.example.ligashome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onOpenPokemons: () -> Unit,
) {
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
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            LeagueCard(label = "Indigo", onClick = onOpenPokemons)
            LeagueCard(label = "Jonton", onClick = onOpenPokemons)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LeagueCard(label: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .height(120.dp)
            .width(60.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = label, modifier = Modifier.align(Alignment.Center))
        }
    }
}
