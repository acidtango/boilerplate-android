package com.example.pokemon_data.repository

import com.example.pokemon_data.remote.PokemonApi
import com.example.pokemon_domain.models.Pokemon
import com.example.pokemon_domain.models.Pokemons
import javax.inject.Inject

class PokemonRepositoryImpl @Inject
constructor(
    private val api: PokemonApi
) : com.example.pokemon_domain.repository.PokemonRepository {
    override suspend fun pokemons(offset: Int, limit: Int): Pokemons {
        val pokemonsNetwork = api.getPokemons(offset, limit)

        val pokemonsDomain = pokemonsNetwork.pokemonsInfo.map { pokemonNetwork ->
            Pokemon(pokemonNetwork.name, pokemonNetwork.url)
        }
        return Pokemons(count = pokemonsNetwork.count, pokemonsInfo = pokemonsDomain)
    }
}
