package com.example.pokedex.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex.R
import kotlinx.android.synthetic.main.fragment_pokedex_details.*
import kotlinx.android.synthetic.main.fragment_pokedex_details.pokemon_name

class PokedexDetailsFragment : Fragment(R.layout.fragment_pokedex_details) {

    private val args: PokedexDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_pokedex_details, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    private fun setupView(view: View) {
        Glide.with(view).load(args.pokemon.imageUrl).into(pokemon_img_details)
        pokemon_name.text = args.pokemon.formattedName
        pokemon_number.text = args.pokemon.formattedNumber
        pokemon_primary_type.text = args.pokemon.types[0].name
        if(args.pokemon.types.size > 1) pokemon_second_type.text = args.pokemon.types[1].name
        else {
            card_second_type.visibility = View.GONE
            val params = card_primary_type.layoutParams as ConstraintLayout.LayoutParams
            params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        }
    }

}
