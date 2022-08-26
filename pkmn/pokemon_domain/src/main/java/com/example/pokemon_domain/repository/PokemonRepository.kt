package com.example.pokemon_domain.repository

import com.example.core.Resource
import com.example.pokemon_domain.models.Pokemons
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun pokemons(offset: Int = 0, limit: Int = 20): Flow<Resource<Pokemons>>
}
