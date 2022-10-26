package com.example.pokedex.view

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.util.PokemonTypesColors
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pokedex_details.*
import kotlinx.android.synthetic.main.fragment_pokedex_details.pokemon_name
import java.util.*

@AndroidEntryPoint
class PokedexDetailsFragment : Fragment(R.layout.fragment_pokedex_details) {

    private val args: PokedexDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun setupView(view: View) {
        val pokemonBackground = card_img
        val primaryColor =
            args.pokemon.types?.getOrNull(0).let {
                resources.getColor(PokemonTypesColors.getTypeColor(it!!))
            }
        args.pokemon.let {
            val secondColor = resources.getColor(R.color.white)
            val drawable = GradientDrawable().apply {
                colors = intArrayOf(
                    primaryColor,
                    if (args.pokemon.types?.size!! > 1) resources.getColor(PokemonTypesColors.getTypeColor(args.pokemon.types?.getOrNull(1).let { it!! })) else secondColor
                )
                orientation = GradientDrawable.Orientation.BOTTOM_TOP
                gradientType = GradientDrawable.LINEAR_GRADIENT
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 24f
            }
            pokemonBackground.background = drawable
        }
        val formattedName = args.pokemon.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
        val number = args.pokemon.imageUrl
        val formattedNumber = when {
            number.length < 2 -> "00$number"
            number.length < 3 -> "0$number"
            else -> number
        }
        Glide.with(view).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png").into(pokemon_img_details)
        pokemon_name.text = formattedName
        pokemon_number.text = formattedNumber
        args.pokemon.types?.getOrNull(0).let {
             pokemon_primary_type.text = it
             pokemon_primary_type.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(PokemonTypesColors.getTypeColor(it!!)))

         }
        if (args.pokemon.types?.size!! > 1) {
            args.pokemon.types?.getOrNull(1).let {
                pokemon_second_type.text = it
                pokemon_second_type.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), PokemonTypesColors.getTypeColor(it!!)))

            }
        } else pokemon_second_type.visibility = View.GONE
    }
}
