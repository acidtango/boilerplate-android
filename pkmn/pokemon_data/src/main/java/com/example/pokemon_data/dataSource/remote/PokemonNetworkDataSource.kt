package com.example.pokemon_data.dataSource.remote

import com.example.core.Resource
import com.example.pokemon_data.models.PokemonsNetwork
import javax.inject.Inject

class PokemonNetworkDataSource @Inject
constructor(
    private val api: PokemonApi
) {
    suspend fun getPokemons(offset: Int, limit: Int): Resource<PokemonsNetwork> {
        return try {
            val pokemonsNetwork = api.getPokemons(offset, limit)
            Resource.Success(pokemonsNetwork)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}
