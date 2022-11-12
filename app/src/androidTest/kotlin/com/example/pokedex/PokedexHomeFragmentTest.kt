package com.example.pokedex

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.pokedex.data.Pokemon
import com.example.pokedex.data.PokemonType
import com.example.pokedex.ui.home.PokedexHomeFragment
import com.example.pokedex.ui.home.PokedexHomeFragmentDirections
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@HiltAndroidTest
class PokedexHomeFragmentTest {

    val list: List<PokemonType> = listOf(PokemonType("grass"), PokemonType( "poison"))
    fun mockPokemon() = Pokemon(
        number = "3",
        name = "venusaur",
        types = list,
        imageUrl = "3"
    )

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testLaunchFragmentInHiltContainer() {
        launchFragmentInHiltContainer<PokedexHomeFragment> {
        }
    }

    @Test
    fun navigate_to_detailsPokedex() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<PokedexHomeFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.pokemon_recycler))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
            )

        verify(navController).navigate(
            PokedexHomeFragmentDirections.actionFirstDestinationToPokedexDetailsFragment(mockPokemon())
        )
    }
}
