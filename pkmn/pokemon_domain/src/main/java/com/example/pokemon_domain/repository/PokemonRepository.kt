package com.example.pokemon_domain.repository

import com.example.pokemon_domain.models.Pokemons

interface PokemonRepository {
    suspend fun pokemons(offset: Int, limit: Int): Pokemons
}
