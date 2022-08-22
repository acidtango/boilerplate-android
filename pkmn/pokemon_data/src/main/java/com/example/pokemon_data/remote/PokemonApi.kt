package com.example.pokemon_data.remote

import com.example.pokemon_data.models.Pokemons
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Pokemons
}
