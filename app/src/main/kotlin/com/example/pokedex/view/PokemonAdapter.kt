package com.example.pokedex.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.Pokemon
import com.example.pokedex.util.PokemonTypesColors
import kotlinx.android.synthetic.main.pokemon_row.view.*
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
                item.let {
                    val formattedName = it.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
                    val number = it.imageUrl
                    val primaryColor = PokemonTypesColors.getTypeColor(it.types?.getOrNull(0).let { it!! } )
                    val secondColor = if (it.types?.size!! > 1) PokemonTypesColors.getTypeColor(it.types.getOrNull(1).let { it!! }) else R.color.white
                    val formattedNumber = when {
                        number.length < 2 -> "00$number"
                        number.length < 3 -> "0$number"
                        else -> number
                    }
                    pokecard.setColors(primaryColor, secondColor)
                    pokecard.setPokeImage("https://assets.pokemon.com/assets/cms2/img/pokedex/full/$formattedNumber.png")
                    pokecard.setPokeName(formattedName)
                    pokecard.setPokeType(it.types.getOrNull(0).let { it!! })
                    if (it.types.size > 1) pokecard.setPokeStype(it.types.getOrNull(1).let { it!! }) else pokecard.setPokeStype("")
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
