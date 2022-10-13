package com.example.pokedex.view

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.data.Pokemon
import com.example.pokedex.util.PokemonTypesColors
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_pokedex_details.*
import java.util.*

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.number == newItem.number && oldItem.imageUrl == newItem.imageUrl &&
                oldItem.name == newItem.name && oldItem.types == newItem.types
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var pokemons: List<Pokemon>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.pokemon_row,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pokemons[position]
        holder.bindView(item, onItemClickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: Pokemon, onItemClickListener: ((Pokemon) -> Unit)? = null) {
            with(itemView) {
                val pokemonBackground = findViewById<CardView>(R.id.home_background)
                val pokemon = findViewById<ImageView>(R.id.pokemon_img)
                val pokemonName = findViewById<TextView>(R.id.pokemon_name)
               // val pokemonCapture = findViewById<TextView>(R.id.pokemon_capture)
                val pokemonType = findViewById<Chip>(R.id.pokemon_type)
                val pokemonSecondType = findViewById<Chip>(R.id.pokemon_second_type)
                item.let {
                    val formattedName = it.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                    val number = it.imageUrl
                    val primaryColor = resources.getColor(PokemonTypesColors.getTypeColor(it.types[0].name))
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

                    val formattedNumber = when {
                        number.length < 2 -> "00$number"
                        number.length < 3 -> "0$number"
                        else -> number
                    }
                    Glide.with(itemView.context).load("https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png").into(pokemon)
                    pokemonName.text = formattedName
                    pokemonType.text = it.types[0].name
                    if (it.types.size > 1) {
                        pokemonSecondType.text = it.types[1].name
                        pokemonSecondType.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context, PokemonTypesColors.getTypeColor(it.types[1].name)))
                    } else {
                        pokemonSecondType.visibility = View.GONE
                    }
                    pokemonType.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(context, PokemonTypesColors.getTypeColor(it.types[0].name)))
                }
                itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Pokemon) -> Unit)? = null

    fun setOnClickListener(listener: (Pokemon) -> Unit) {
        onItemClickListener = listener
    }
}
