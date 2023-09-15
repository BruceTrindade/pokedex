package com.example.pokedex.main.details

import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.pokedex.MockPokemon
import com.example.pokedex.MockPokemon.navController
import com.example.pokedex.R
import com.example.pokedex.launchFragmentInHiltContainer
import com.example.pokedex.pokemonsdetails.details.PokedexDetailsFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class PokedexDetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
       
        launchFragmentInHiltContainer<PokedexDetailsFragment>(fragmentArgs = bundleOf("pokemon" to MockPokemon.mockPokemon)) {
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun checkIfAfterPassArgumentThePokemonNameIsCorrect() {
        Espresso.onView(withId(R.id.pokemon_name))
            .check(ViewAssertions.matches(ViewMatchers.withText("Venusaur")))
    }

    @Test
    fun checkIfAfterPassArgumentThePokemonNumberIsCorrect() {
        Espresso.onView(withId(R.id.pokemon_number))
            .check(ViewAssertions.matches(ViewMatchers.withText("003")))
    }

    @Test
    fun checkIfAfterPassArgumentThePokemonTypeIsCorrect() {
        Espresso.onView(withId(R.id.pokemon_primary_type))
            .check(ViewAssertions.matches(ViewMatchers.withText("grass")))
    }

    @Test
    fun checkIfAfterPassArgumentThePokemonSecondTypeIsCorrect() {
        Espresso.onView(withId(R.id.pokemon_second_type))
            .check(ViewAssertions.matches(ViewMatchers.withText("poison")))
    }
}
