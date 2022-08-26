package com.example.pokemon_data.dataSource.remote.networkMappers

import com.example.pokemon_data.dataSource.local.PokemonEntity
import com.example.pokemon_data.models.PokemonNetwork
import com.example.pokemon_data.models.PokemonsNetwork
import com.example.pokemon_domain.models.Pokemon
import com.example.pokemon_domain.models.Pokemons

fun PokemonNetwork.toDomain(): Pokemon {
    return Pokemon(
        name = this.name,
        url = this.url
    )
}

fun PokemonsNetwork.toDomain(): Pokemons {
    return Pokemons(
        count = this.count,
        pokemonsInfo = this.pokemonsInfo.map { it.toDomain() }
    )
}

fun PokemonNetwork.toEntity(): PokemonEntity {
    return PokemonEntity(
        name = this.name,
        url = this.url
    )
}
