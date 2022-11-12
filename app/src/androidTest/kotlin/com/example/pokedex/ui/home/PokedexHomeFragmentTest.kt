package com.example.pokedex.ui.home

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.pokedex.MockPokemon.mockPokemon
import com.example.pokedex.MockPokemon.navController
import com.example.pokedex.R
import com.example.pokedex.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@HiltAndroidTest
class PokedexHomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()

        launchFragmentInHiltContainer<PokedexHomeFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun navigate_to_detailsPokedex() {
        onView(withId(R.id.pokemon_recycler))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
            )

        verify(navController).navigate(
            PokedexHomeFragmentDirections.actionFirstDestinationToPokedexDetailsFragment(mockPokemon)
        )
    }
}
