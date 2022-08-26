package com.example.pokemon_data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.core.Resource
import com.example.pokemon_data.dataSource.local.PokemonDatabase
import com.example.pokemon_data.dataSource.local.PokemonLocalDataSource
import com.example.pokemon_data.dataSource.remote.PokemonApi
import com.example.pokemon_data.dataSource.remote.PokemonNetworkDataSource
import com.example.pokemon_data.fixtures.pokemonsJson
import com.example.pokemon_domain.models.Pokemon
import com.example.pokemon_domain.models.Pokemons
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"], sdk = [32])
class PokemonRepositoryTest {

    private lateinit var pokemonRepository: PokemonRepositoryImpl
    private lateinit var database: PokemonDatabase
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: PokemonApi

    lateinit var instrumentationContext: Context

    @Before
    fun setup() {
        // LOCAL
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PokemonDatabase::class.java
        )
            .allowMainThreadQueries().build()

        // NETWORK
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        api = Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create()
            )
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(PokemonApi::class.java)

        pokemonRepository = PokemonRepositoryImpl(
            network = PokemonNetworkDataSource(
                api = api
            ),
            local = PokemonLocalDataSource(pokemonDao = database.pokemonDao())
        )
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun `Get pokemons`() {
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(pokemonsJson)
            )

            pokemonRepository.pokemons(0, 1).collectLatest {
                if (it is Resource.Success) {
                    assertThat(it.data!!).isEqualTo(
                        Pokemons(
                            count = 1154,
                            listOf(Pokemon(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"))
                        )
                    )
                } else throw Exception("Something went wrong")
            }
        }
    }
}
