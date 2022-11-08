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
import com.example.pokedex.util.formatteName
import com.example.pokedex.util.formatteNumber
import com.example.pokedex.util.formattedImageLink
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pokedex_details.*
import kotlinx.android.synthetic.main.fragment_pokedex_details.pokemon_name

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
        val verifyTypeSize = args.pokemon.types.size > 1
        val pokemonBackground = card_img
        val primaryColor = resources.getColor(PokemonTypesColors.getTypeColor(args.pokemon.types[0].name))
        args.pokemon.let {
            val secondColor = resources.getColor(R.color.white)
            val drawable = GradientDrawable().apply {
                colors = intArrayOf(
                    primaryColor,
                    if (verifyTypeSize) resources.getColor(PokemonTypesColors.getTypeColor(it.types[1].name)) else secondColor
                )
                orientation = GradientDrawable.Orientation.BOTTOM_TOP
                gradientType = GradientDrawable.LINEAR_GRADIENT
                shape = GradientDrawable.RECTANGLE
                cornerRadius = 24f
            }
            pokemonBackground.background = drawable
        }
        Glide.with(view).load(args.pokemon.imageUrl.formattedImageLink()).into(pokemon_img_details)
        pokemon_name.text = args.pokemon.name.formatteName()
        pokemon_number.text = args.pokemon.imageUrl.formatteNumber()
        pokemon_primary_type.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(PokemonTypesColors.getTypeColor(args.pokemon.types[0].name)))
        pokemon_primary_type.text = args.pokemon.types[0].name
        if (verifyTypeSize) {
            pokemon_second_type.text = args.pokemon.types[1].name
            pokemon_second_type.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), PokemonTypesColors.getTypeColor(args.pokemon.types[1].name)))
        } else pokemon_second_type.visibility = View.GONE
    }
}
