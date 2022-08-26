package com.example.pokemon_data.dataSource.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.pokemon_data.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"], sdk = [32])
class PokemonDaoTest {
    private lateinit var database: PokemonDatabase
    private lateinit var pokemonEntity: PokemonEntity

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    /*@get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()*/

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PokemonDatabase::class.java
        )
            .allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun `PokemonDatabase is empty`() {
        runBlocking {
            assertThat(
                database.pokemonDao().getPokemons(0, 1)
            ).isEmpty()
        }
    }

    @Test
    fun `Insert pikachu into PokemonDatabase`() {
        runBlocking {
            pokemonEntity = PokemonEntity(id = null, name = "Pikachu", url = "")

            database.pokemonDao().insertOrUpdate(
                pokemonEntity
            )

            assertThat(
                database.pokemonDao().getPokemons(0, 1)
            ).isEqualTo(listOf(PokemonEntity(id = 1, name = "Pikachu", url = "")))
        }
    }

    @Test
    fun `Insert 2 pikachu's into PokemonDatabase, only adds 1`() {
        runBlocking {
            pokemonEntity = PokemonEntity(id = null, name = "Pikachu", url = "")

            database.pokemonDao().insertOrUpdate(
                pokemonEntity
            )

            database.pokemonDao().insertOrUpdate(
                pokemonEntity
            )

            assertThat(
                database.pokemonDao().getPokemons(0, 1)
            ).isEqualTo(listOf(PokemonEntity(id = 1, name = "Pikachu", url = "")))
        }
    }

    @Test
    fun `Insert multiples pokemons into PokemonDatabase`() {
        runBlocking {
            pokemonEntity = PokemonEntity(id = null, name = "Pikachu", url = "")
            val articuno = PokemonEntity(id = null, name = "Articuno", url = "")
            val zapdos = PokemonEntity(id = null, name = "Zapdos", url = "")
            val moltres = PokemonEntity(id = null, name = "Moltres", url = "")

            database.pokemonDao().insertOrUpdate(
                pokemonEntity
            )

            database.pokemonDao().insertOrUpdate(
                articuno
            )

            database.pokemonDao().insertOrUpdate(
                zapdos
            )

            database.pokemonDao().insertOrUpdate(
                moltres
            )

            val expected = listOf(
                PokemonEntity(id = 1, name = "Pikachu", url = ""),
                PokemonEntity(id = 2, name = "Articuno", url = ""),
                PokemonEntity(id = 3, name = "Zapdos", url = ""),
                PokemonEntity(id = 4, name = "Moltres", url = "")
            )
            assertThat(
                database.pokemonDao().getPokemons(0, 4)
            ).isEqualTo(expected)
        }
    }

    @Test
    fun `Insert multiples pokemons get only offset and limit`() {
        runBlocking {
            pokemonEntity = PokemonEntity(id = null, name = "Pikachu", url = "")
            val articuno = PokemonEntity(id = null, name = "Articuno", url = "")
            val zapdos = PokemonEntity(id = null, name = "Zapdos", url = "")
            val moltres = PokemonEntity(id = null, name = "Moltres", url = "")

            database.pokemonDao().insertOrUpdate(
                pokemonEntity
            )

            database.pokemonDao().insertOrUpdate(
                articuno
            )

            database.pokemonDao().insertOrUpdate(
                zapdos
            )

            database.pokemonDao().insertOrUpdate(
                moltres
            )

            val expected = listOf(
                PokemonEntity(id = 3, name = "Zapdos", url = ""),
                PokemonEntity(id = 4, name = "Moltres", url = "")
            )
            assertThat(
                database.pokemonDao().getPokemons(2, 4)
            ).isEqualTo(expected)
        }
    }
}
