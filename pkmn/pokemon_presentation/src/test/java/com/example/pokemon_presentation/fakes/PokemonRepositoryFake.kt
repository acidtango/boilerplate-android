package com.example.pokemon_presentation.fakes

import com.example.core.Resource
import com.example.pokemon_domain.models.Pokemon
import com.example.pokemon_domain.models.Pokemons
import com.example.pokemon_domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryFake : PokemonRepository {
    override suspend fun pokemons(offset: Int, limit: Int): Flow<Resource<Pokemons>> {
        return flow {
            if (offset == 0) {
                emit(
                    Resource.Success(
                        Pokemons(
                            count = 5,
                            pokemonsInfo = listOf(Pokemon("Pikachu", ""), Pokemon("Raichu", ""))
                        )
                    )
                )
                return@flow
            }

            emit(
                Resource.Success(
                    Pokemons(
                        count = 5,
                        pokemonsInfo = listOf(
                            Pokemon("Articuno", ""),
                            Pokemon("Zapdos", ""),
                            Pokemon("Moltres", "")
                        )
                    )
                )
            )
        }
    }
}
