package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.data.Pokemon
import com.example.core.utils.formatteName
import com.example.core.utils.formattedImageLink
import com.example.dsmpokedex.PokeChips
import com.example.ui.theme.pokemonTypeColor

/**
 * Grid card for a single Pokémon. Compose replacement for the design-system
 * `CardViewPoke`. Background is tinted by the primary type.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
) {
    val primaryColor = pokemonTypeColor(pokemon.types.first().name)

    Card(
        onClick = { onClick(pokemon) },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = primaryColor),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = pokemon.name.formatteName(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(top = 4.dp),
            ) {
                pokemon.types.forEach { type ->
                    PokeChips(
                        text = type.name.formatteName(),
                        primaryColor = pokemonTypeColor(type.name).toArgb(),
                    )
                }
            }
            AsyncImage(
                model = pokemon.imageUrl.formattedImageLink(),
                contentDescription = pokemon.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.End)
                    .height(96.dp),
            )
        }
    }
}
