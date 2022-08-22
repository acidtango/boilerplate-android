package com.example.pokemon_data.repository

import com.example.pokemon_data.modelsDomain.Pokemons

interface PokemonRepository {
    suspend fun pokemons(offset: Int, limit: Int): Pokemons
}
