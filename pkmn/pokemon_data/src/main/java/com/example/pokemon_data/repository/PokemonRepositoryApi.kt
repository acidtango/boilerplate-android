package com.example.pokemon_data.repository

import com.example.core.Resource
import com.example.pokemon_data.remote.PokemonApi
import com.example.pokemon_domain.models.Pokemon
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
            val pokemonsDomain = pokemonsNetwork.pokemonsInfo.map { pokemonNetwork ->
                Pokemon(pokemonNetwork.name, pokemonNetwork.url)
            }
            Resource.Success(Pokemons(count = pokemonsNetwork.count, pokemonsInfo = pokemonsDomain))
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}
