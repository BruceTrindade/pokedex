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
import com.example.pokedex.util.formattedImageLink
import com.example.pokedex.util.formatteName
import kotlinx.android.synthetic.main.pokemon_row.view.*

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
                    val verifyTypeSize = it.types.size > 1
                    val formattedName = it.name.formatteName()
                    val primaryColor = PokemonTypesColors.getTypeColor(it.types[0].name)
                    val secondColor = if (verifyTypeSize) PokemonTypesColors.getTypeColor(it.types[1].name.formatteName()) else R.color.white
                    pokecard.setPokeType(it.types[0].name.formatteName())
                    if (verifyTypeSize) pokecard.setPokeSecondType(it.types[1].name.formatteName()) else pokecard.setPokeSecondType("")
                    pokecard.setPokeImage(it.imageUrl.formattedImageLink())
                    pokecard.setPokeName(formattedName)

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
