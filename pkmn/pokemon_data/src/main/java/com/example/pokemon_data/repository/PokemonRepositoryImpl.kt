package com.example.pokemon_data.repository

import android.util.Log
import com.example.pokemon_data.remote.PokemonApi
import com.example.pokemon_domain.models.Pokemons
import javax.inject.Inject

class PokemonRepositoryImpl @Inject
constructor(
    private val api: PokemonApi
) : com.example.pokemon_domain.repository.PokemonRepository {
    override suspend fun pokemons(offset: Int, limit: Int): Pokemons {
        val pokemonsNetwork = api.getPokemons(offset, limit)

        Log.d("POKEMONS1", pokemonsNetwork.toString())
        return Pokemons(count = 1, pokemonsInfo = listOf())
    }
}
