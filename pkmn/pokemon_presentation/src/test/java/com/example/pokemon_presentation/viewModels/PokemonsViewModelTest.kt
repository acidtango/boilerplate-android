package com.example.pokemon_presentation.viewModels

import com.example.pokemon_domain.models.Pokemon
import com.example.pokemon_domain.models.Pokemons
import com.example.pokemon_domain.useCases.GetPokemonsUseCase
import com.example.pokemon_presentation.MainCoroutineRule
import com.example.pokemon_presentation.fakes.PokemonRepositoryFake
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonsViewModelTest {
    private lateinit var pokemonsViewModel: PokemonsViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        pokemonsViewModel =
            PokemonsViewModel(pokemonsUseCase = GetPokemonsUseCase(PokemonRepositoryFake()))
    }

    @Test
    fun `First pokemons are Pikachu and Raichu`() {
        runBlocking {
            assertThat(pokemonsViewModel.state).isEqualTo(
                Pokemons(
                    count = 5,
                    pokemonsInfo = listOf(Pokemon("Pikachu", ""), Pokemon("Raichu", ""))
                )
            )
        }
    }

    @Test
    fun `Page value is 1`() {
        runBlocking {
            assertThat(pokemonsViewModel.page.value).isEqualTo(1)
        }
    }

    @Test
    fun `Next pokemons adds the 3 legendary birds`() {
        runBlocking {
            pokemonsViewModel.nextPage()
            assertThat(pokemonsViewModel.state).isEqualTo(
                Pokemons(
                    count = 5,
                    pokemonsInfo = listOf(
                        Pokemon("Pikachu", ""), Pokemon("Raichu", ""), Pokemon("Articuno", ""),
                        Pokemon("Zapdos", ""),
                        Pokemon("Moltres", "")

                    )
                )
            )
        }
    }

    @Test
    fun `Page value is 2 after next`() {
        runBlocking {
            pokemonsViewModel.nextPage()
            assertThat(pokemonsViewModel.page.value).isEqualTo(2)
        }
    }

    @Test
    fun `Double next case should not add more birds`() {
        runBlocking {
            pokemonsViewModel.nextPage()
            pokemonsViewModel.nextPage()
            assertThat(pokemonsViewModel.state).isEqualTo(
                Pokemons(
                    count = 5,
                    pokemonsInfo = listOf(
                        Pokemon("Pikachu", ""), Pokemon("Raichu", ""), Pokemon("Articuno", ""),
                        Pokemon("Zapdos", ""),
                        Pokemon("Moltres", "")

                    )
                )
            )
        }
    }
}
