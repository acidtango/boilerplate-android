package com.example.pokemon_data.dataSource.remote

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.example.core.Resource
import com.example.pokemon_data.fixtures.pokemonsJson
import com.example.pokemon_data.models.PokemonNetwork
import com.example.pokemon_data.models.PokemonsNetwork
import com.google.common.truth.Truth.assertThat
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
import org.robolectric.shadows.ShadowLog
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"], sdk = [32])
class PokemonNetworkDataSourceTest {

    private lateinit var networkDataSource: PokemonNetworkDataSource
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: PokemonApi

    lateinit var instrumentationContext: Context

    @Before
    fun setUp() {
        ShadowLog.stream = System.out
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

        networkDataSource = PokemonNetworkDataSource(
            api = api
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get Pokemons`() {
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(pokemonsJson)
            )

            val response = networkDataSource.getPokemons(0, 1)

            println(response.exception?.stackTraceToString())
            if (response is Resource.Success) {
                assertThat(response.data!!).isEqualTo(
                    PokemonsNetwork(
                        count = 1154,
                        pokemonsInfo = listOf(
                            PokemonNetwork(
                                name = "bulbasaur",
                                url = "https://pokeapi.co/api/v2/pokemon/1/"
                            )
                        )
                    )
                )
            } else throw Exception("Something went wrong")
        }
    }

    @Test
    fun `Error 404`() {
        runBlocking {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(404)
            )

            val response = networkDataSource.getPokemons(0, 1)

            println(response.exception?.message)
            if (response is Resource.Error) {
                assertThat(response.exception!!.message).isEqualTo(
                    "HTTP 404 Client Error"
                )
            } else throw Exception("Something went wrong")
        }
    }
}
