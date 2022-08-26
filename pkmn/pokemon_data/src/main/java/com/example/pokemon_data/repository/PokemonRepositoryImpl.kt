package com.example.pokemon_data.repository

import com.example.core.Resource
import com.example.pokemon_data.dataSource.local.PokemonLocalDataSource
import com.example.pokemon_data.dataSource.local.toDomain
import com.example.pokemon_data.dataSource.remote.PokemonNetworkDataSource
import com.example.pokemon_data.dataSource.remote.networkMappers.toEntity
import com.example.pokemon_domain.models.Pokemons
import com.example.pokemon_domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl
@Inject
constructor(
    private val network: PokemonNetworkDataSource,
    private val local: PokemonLocalDataSource
) : PokemonRepository {
    override suspend fun pokemons(offset: Int, limit: Int): Flow<Resource<Pokemons>> {
        return flow {
            try {
                var count = 0
                val response = network.getPokemons(offset, limit)

                if (response is Resource.Success) {
                    count = response.data!!.count
                    response.data!!.pokemonsInfo.forEach { pokemon ->
                        local.insert(pokemon.toEntity())
                    }
                }

                val localPokemons = local.getPokemons(offset, limit)

                val pokemons = localPokemons.map { pokemonEntity ->
                    pokemonEntity.toDomain()
                }

                if (count == 0) count = 1000

                emit(Resource.Success(Pokemons(count = count, pokemonsInfo = pokemons)))
            } catch (e: Exception) {
                emit(Resource.Error(e))
            }
        }
    }
}
