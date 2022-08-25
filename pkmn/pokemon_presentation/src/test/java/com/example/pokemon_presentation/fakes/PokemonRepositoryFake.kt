package com.example.pokemon_presentation.fakes

import com.example.core.Resource
import com.example.pokemon_domain.models.Pokemon
import com.example.pokemon_domain.models.Pokemons
import com.example.pokemon_domain.repository.PokemonRepository

class PokemonRepositoryFake : PokemonRepository {
    override suspend fun pokemons(offset: Int, limit: Int): Resource<Pokemons> {

        if (offset == 0) return Resource.Success(
            Pokemons(
                count = 5,
                pokemonsInfo = listOf(Pokemon("Pikachu", ""), Pokemon("Raichu", ""))
            )
        )

        return Resource.Success(
            Pokemons(
                count = 5,
                pokemonsInfo = listOf(
                    Pokemon("Articuno", ""),
                    Pokemon("Zapdos", ""),
                    Pokemon("Moltres", "")
                )
            )
        )
    }
}
