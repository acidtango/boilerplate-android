package com.example.pokemon_data.repository

import com.example.core.Resource
import com.example.pokemon_data.dataSource.remote.networkMappers.toDomain
import com.example.pokemon_data.dataSource.remote.PokemonApi
import com.example.pokemon_domain.models.Pokemons
import com.example.pokemon_domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryApi @Inject
constructor(
    private val api: PokemonApi
) : PokemonRepository {
    override suspend fun pokemons(offset: Int, limit: Int): Resource<Pokemons> {
        return try {
            val pokemonsNetwork = api.getPokemons(offset, limit)
            Resource.Success(pokemonsNetwork.toDomain())
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}
