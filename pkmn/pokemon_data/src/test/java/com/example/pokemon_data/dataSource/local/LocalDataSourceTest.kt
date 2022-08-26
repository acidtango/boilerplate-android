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
class LocalDataSourceTest {
    private lateinit var database: PokemonDatabase
    private lateinit var pokemonDao: PokemonDao
    private lateinit var pokemonLocalDataSource: PokemonLocalDataSource

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

        pokemonDao = database.pokemonDao()

        pokemonLocalDataSource = PokemonLocalDataSource(pokemonDao = pokemonDao)
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun `Insert pokemon`() {
        runBlocking {
            pokemonLocalDataSource.insert(PokemonEntity(id = null, name = "Arceus", url = ""))

            assertThat(
                pokemonLocalDataSource.getPokemons(
                    0,
                    1
                )
            ).isEqualTo(listOf(PokemonEntity(id = 1, name = "Arceus", url = "")))
        }
    }
}
