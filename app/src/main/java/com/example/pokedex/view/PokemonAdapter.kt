package com.example.pokedex.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.domain.Pokemon

class PokemonAdapter(
//    private val items: List<Pokemon>,
//    private val onItemClicked : (Pokemon) -> Unit,
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.formattedNumber == newItem.formattedNumber && oldItem.imageUrl == newItem.imageUrl &&
                oldItem.formattedName == newItem.formattedName && oldItem.types == newItem.types
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

        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon) {
            with(itemView) {
                val pokemon = findViewById<ImageView>(R.id.pokemon_img)
//            val pokemonNumber = findViewById<TextView>(R.id.pokemon_number)
                val pokemonName = findViewById<TextView>(R.id.pokemon_name)
//            val pokemonPrimaryType = findViewById<TextView>(R.id.pokemon_primary_type)
//            val pokemonSecondType = findViewById<TextView>(R.id.pokemon_second_type)

                item.let {
                    Glide.with(itemView.context).load(it.imageUrl).into(pokemon)
//                pokemonNumber.text = "Nº ${item.formattedNumber}"
                    pokemonName.text = item.formattedName
//                pokemonPrimaryType.text = item.types[0].name
                    // when
//                if (item.types.size > 1 ) pokemonSecondType.text = item.types[1].name
//                else pokemonSecondType.visibility = View.GONE
                }
//            itemView.setOnClickListener {
//                onItemClicked(item)
//            }
            }
        }
    }
}

/*
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

  <androidx.cardview.widget.CardView
      android:id="@+id/left"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_marginStart="5dp"
      android:layout_marginLeft="5dp"
      android:layout_marginTop="16dp"
      android:layout_marginEnd="3dp"
      android:layout_marginRight="3dp"
      app:layout_constraintEnd_toStartOf="@+id/right"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toTopOf="parent">

      <androidx.constraintlayout.widget.ConstraintLayout
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:layout_marginTop="8dp"
          android:layout_marginBottom="8dp">

          <ImageView
              android:id="@+id/pokemon_img"
              android:layout_width="90dp"
              android:layout_height="90dp"
              app:layout_constraintEnd_toEndOf="parent"
              app:layout_constraintStart_toStartOf="parent"
              app:layout_constraintTop_toTopOf="parent" />

          <TextView
              android:id="@+id/pokemon_number"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_marginStart="80dp"
              android:layout_marginLeft="30dp"
              app:layout_constraintStart_toStartOf="parent"
              app:layout_constraintTop_toBottomOf="@+id/pokemon_img"
              android:textColor="#919191"
              android:textSize="14sp"
              tools:text="N 004"/>

          <TextView
              android:id="@+id/pokemon_name"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_marginStart="80dp"
              android:textColor="#313131"
              android:textSize="24sp"
              app:layout_constraintStart_toStartOf="parent"
              app:layout_constraintTop_toBottomOf="@+id/pokemon_number"
              tools:text="Charmander" />

          <TextView
              android:id="@+id/pokemon_primary_type"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:layout_marginStart="80dp"
              android:background="#212121"
              android:gravity="center_horizontal"
              android:textAlignment="center"
              android:textColor="#FFFFFF"
              android:textSize="12sp"
              app:layout_constraintStart_toStartOf="parent"
              app:layout_constraintTop_toBottomOf="@+id/pokemon_name"
              tools:text="Fire" />

          <TextView
              android:id="@+id/pokemon_second_type"
              android:layout_width="wrap_content"
              android:layout_height="wrap_content"
              android:background="#212121"
              android:layout_marginStart="20dp"
              android:gravity="center_horizontal"
              android:textAlignment="center"
              android:textColor="#FFFFFF"
              android:textSize="12sp"
              app:layout_constraintStart_toEndOf="@+id/pokemon_primary_type"
              app:layout_constraintTop_toBottomOf="@+id/pokemon_name"
              tools:text="Water" />

      </androidx.constraintlayout.widget.ConstraintLayout>

  </androidx.cardview.widget.CardView>

    <androidx.cardview.widget.CardView
        android:id="@+id/right"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="3dp"
        android:layout_marginLeft="3dp"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="3dp"
        android:layout_marginRight="3dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/left"
        app:layout_constraintTop_toTopOf="parent">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:layout_marginBottom="8dp">

            <ImageView
                android:id="@+id/pokemon_img2"
                android:layout_width="90dp"
                android:layout_height="90dp"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />

            <TextView
                android:id="@+id/pokemon_number2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="80dp"
                android:layout_marginLeft="30dp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/pokemon_img2"
                android:textColor="#919191"
                android:textSize="14sp"
                tools:text="N 004"/>

            <TextView
                android:id="@+id/pokemon_name2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="80dp"
                android:textColor="#313131"
                android:textSize="24sp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/pokemon_number2"
                tools:text="Charmander" />

            <TextView
                android:id="@+id/pokemon_primary_type2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginStart="80dp"
                android:background="#212121"
                android:gravity="center_horizontal"
                android:textAlignment="center"
                android:textColor="#FFFFFF"
                android:textSize="12sp"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toBottomOf="@+id/pokemon_name2"
                tools:text="Fire" />

            <TextView
                android:id="@+id/pokemon_second_type2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:background="#212121"
                android:layout_marginStart="20dp"
                android:gravity="center_horizontal"
                android:textAlignment="center"
                android:textColor="#FFFFFF"
                android:textSize="12sp"
                app:layout_constraintStart_toEndOf="@+id/pokemon_primary_type2"
                app:layout_constraintTop_toBottomOf="@+id/pokemon_name2"
                tools:text="Water" />

        </androidx.constraintlayout.widget.ConstraintLayout>

    </androidx.cardview.widget.CardView>





</androidx.constraintlayout.widget.ConstraintLayout>
 */
