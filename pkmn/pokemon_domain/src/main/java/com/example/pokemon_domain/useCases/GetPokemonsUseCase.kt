package com.example.pokemon_domain.useCases

import com.example.pokemon_domain.repository.PokemonRepository

class GetPokemonsUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke() {
        repository.pokemons(0, 10)
    }
}
