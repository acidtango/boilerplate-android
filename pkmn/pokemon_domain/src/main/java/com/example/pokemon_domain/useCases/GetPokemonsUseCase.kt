package com.example.pokemon_domain.useCases

import com.example.pokemon_domain.models.Pokemons
import com.example.pokemon_domain.repository.PokemonRepository

class GetPokemonsUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(): Pokemons {
        return repository.pokemons(0, 10)
    }
}
