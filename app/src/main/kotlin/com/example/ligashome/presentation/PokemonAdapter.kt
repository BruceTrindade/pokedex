package com.example.ligashome.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.Pokemon
import com.example.core.utils.formatteName
import com.example.core.utils.formattedImageLink
import com.example.pokedex.databinding.PokemonItemBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.number == newItem.number
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var pokemons: List<Pokemon>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewHolder(binding)
    }

    override fun getItemCount() = minOf(pokemons.size, 20)

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = pokemons[position]
        with(holder) {
            item.let {
                val verifyTypeSize = it.types.size > 1
                val formattedName = it.name.formatteName()
                binding.pokecard.setPokeType(it.types[0].name.formatteName())
                if (verifyTypeSize) binding.pokecard.setPokeSecondType(it.types[1].name.formatteName()) else binding.pokecard.setPokeSecondType("")
                binding.pokecard.setPokeImage(it.imageUrl.formattedImageLink())
                binding.pokecard.setPokeName(formattedName)
            }
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }
        }
    }

    inner class PokemonViewHolder(val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var onItemClickListener: ((Pokemon) -> Unit)? = null

    fun setOnClickListener(listener: (Pokemon) -> Unit) {
        onItemClickListener = listener
    }
}
