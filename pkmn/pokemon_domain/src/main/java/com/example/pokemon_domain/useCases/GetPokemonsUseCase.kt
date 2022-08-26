package com.example.pokemon_domain.useCases

import com.example.core.Resource
import com.example.pokemon_domain.models.Pokemons
import com.example.pokemon_domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonsUseCase(
    private val repository: PokemonRepository
) {
    operator fun invoke(): Flow<Resource<Pokemons>> {
        return flow {
            repository.pokemons().collect {
                emit(it)
            }
        }
    }

    fun nextPage(offset: Int = 0, limit: Int = 20): Flow<Resource<Pokemons>> {
        return flow {
            repository.pokemons(offset, limit).collect {
                emit(it)
            }
        }
    }
}
