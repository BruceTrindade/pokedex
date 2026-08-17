package com.example.pokemonsdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.core.data.Pokemon
import com.example.core.utils.formatteName
import com.example.core.utils.formatteNumber
import com.example.core.utils.formattedImageLink
import com.example.dsmpokedex.PokeChips
import com.example.ui.theme.pokemonTypeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    pokemon: Pokemon,
    onBack: () -> Unit,
) {
    val hasSecondType = pokemon.types.size > 1
    val primaryColor = pokemonTypeColor(pokemon.types[0].name)
    val secondColor = if (hasSecondType) pokemonTypeColor(pokemon.types[1].name) else Color.White

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(410.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.verticalGradient(listOf(secondColor, primaryColor)),
                    )
                    .padding(16.dp),
            ) {
                AsyncImage(
                    model = pokemon.imageUrl.formattedImageLink(),
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(300.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = pokemon.name.formatteName(),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                    )
                    Text(
                        text = pokemon.imageUrl.formatteNumber(),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 8.dp),
                ) {
                    PokeChips(
                        text = pokemon.types[0].name.formatteName(),
                        primaryColor = primaryColor.toArgb(),
                    )
                    if (hasSecondType) {
                        PokeChips(
                            text = pokemon.types[1].name.formatteName(),
                            primaryColor = secondColor.toArgb(),
                        )
                    }
                }
            }
        }
    }
}
