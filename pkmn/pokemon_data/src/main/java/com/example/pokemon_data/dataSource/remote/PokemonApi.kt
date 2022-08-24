package com.example.pokemon_data.dataSource.remote

import com.example.pokemon_data.models.PokemonsNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonsNetwork
}
