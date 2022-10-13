package com.example.pokedex.view

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.util.PokemonTypesColors
import com.example.pokedex.viewmodel.PokedexDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_pokedex_details.*
import kotlinx.android.synthetic.main.fragment_pokedex_details.pokemon_name
import java.util.*

@AndroidEntryPoint
class PokedexDetailsFragment : Fragment(R.layout.fragment_pokedex_details) {

    private lateinit var viewModel: PokedexDetailsViewModel

    private val args: PokedexDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PokedexDetailsViewModel::class.java)
        setupView(view)
        setupCaptureClick()
    }

    private fun setupView(view: View) {
        val pokemonBackground = card_img
        val primaryColor = resources.getColor(PokemonTypesColors.getTypeColor(args.pokemon.types[0].name))
        args.pokemon.let {
            val secondColor = resources.getColor(R.color.white)
            val drawable = GradientDrawable().apply {
                colors = intArrayOf(
                    primaryColor,
                    if (it.types.size > 1) resources.getColor(PokemonTypesColors.getTypeColor(it.types[1].name)) else secondColor
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
        pokemon_primary_type.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), PokemonTypesColors.getTypeColor(args.pokemon.types[0].name)))
        pokemon_primary_type.text = args.pokemon.types[0].name
        if (args.pokemon.types.size > 1) {
            pokemon_second_type.text = args.pokemon.types[1].name
            pokemon_second_type.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), PokemonTypesColors.getTypeColor(args.pokemon.types[1].name)))
        } else pokemon_second_type.visibility = View.GONE
    }

    private fun setupCaptureClick() {
        pokemon_nav.setOnClickListener {
            val actions = PokedexDetailsFragmentDirections.actionPokedexDetailsFragmentToPokedexCapturedFragment()
            findNavController().navigate(actions)
        }
        pokemon_capture.setOnClickListener {
            viewModel.insert(args.pokemon)
        }
    }
}
