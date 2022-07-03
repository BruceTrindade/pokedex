package com.example.pokedex.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.domain.Pokemon

class PokemonAdapter (
    private val items: List<Pokemon>,
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_row, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon) = with(itemView) {
            val pokemon = findViewById<ImageView>(R.id.pokemon_img)
            val pokemonNumber = findViewById<TextView>(R.id.pokemon_number)
            val pokemonName = findViewById<TextView>(R.id.pokemon_name)
            val pokemonPrimaryType = findViewById<TextView>(R.id.pokemon_primary_type)
            val pokemonSecondType = findViewById<TextView>(R.id.pokemon_second_type)

            pokemonNumber.text = "Nº ${item.number}"
            pokemonName.text = item.name
            pokemonPrimaryType.text = item.types[0].name
            //when
            if (item.types.size > 1 ) pokemonSecondType.text = item.types[1].name
            else pokemonSecondType.visibility = View.GONE
        }
    }
}