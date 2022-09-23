package com.example.pokedex.view

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.util.PokemonTypesColors
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
            }
            pokemonBackground.setBackgroundColor(primaryColor)
        }
        Glide.with(view).load(args.pokemon.imageUrl).into(pokemon_img_details)
        pokemon_name.text = args.pokemon.formattedName
        pokemon_number.text = args.pokemon.formattedNumber
        card_primary_type.setBackgroundColor(primaryColor)
        pokemon_primary_type.text = args.pokemon.types[0].name
        if (args.pokemon.types.size > 1) pokemon_second_type.text = args.pokemon.types[1].name
        else {
            card_second_type.visibility = View.GONE
            val params = card_primary_type.layoutParams as ConstraintLayout.LayoutParams
            params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        }
    }
}

// android:id="@+id/card_img" a cor desse card no fragment estava chrashing o app
