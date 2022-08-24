package com.example.pokemon_domain.useCases

import com.example.core.Resource
import com.example.pokemon_domain.models.Pokemons
import com.example.pokemon_domain.repository.PokemonRepository

class GetPokemonsUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(offset: Int = 0, limit: Int = 20): Resource<Pokemons> {
        return repository.pokemons(offset, limit)
    }
}
