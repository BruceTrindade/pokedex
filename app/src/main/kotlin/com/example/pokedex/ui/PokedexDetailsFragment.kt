package com.example.pokedex.ui

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokedexDetailsBinding
import com.example.pokedex.utils.PokemonTypesColors
import com.example.pokedex.utils.formatteName
import com.example.pokedex.utils.formatteNumber
import com.example.pokedex.utils.formattedImageLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexDetailsFragment : Fragment(R.layout.fragment_pokedex_details) {

    private val args: PokedexDetailsFragmentArgs by navArgs()

    private var _binding: FragmentPokedexDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokedexDetailsBinding.bind(view)
        setupView(view)
    }

    private fun setupView(view: View) {
        val verifyTypeSize = args.pokemon.types.size > 1
        val pokemonBackground = binding.cardImg
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
        Glide.with(view).load(args.pokemon.imageUrl.formattedImageLink()).into(binding.pokemonImgDetails)
        binding.pokemonName.text = args.pokemon.name.formatteName()
        binding.pokemonNumber.text = args.pokemon.imageUrl.formatteNumber()
        binding.pokemonPrimaryType.chipBackgroundColor = ColorStateList.valueOf(resources.getColor(PokemonTypesColors.getTypeColor(args.pokemon.types[0].name)))
        binding.pokemonPrimaryType.text = args.pokemon.types[0].name
        if (verifyTypeSize) {
            binding.pokemonSecondType.text = args.pokemon.types[1].name
            binding.pokemonSecondType.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), PokemonTypesColors.getTypeColor(args.pokemon.types[1].name)))
        } else binding.pokemonSecondType.visibility = View.GONE
    }
}
