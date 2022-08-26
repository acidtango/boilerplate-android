package com.example.pokemon_data.dataSource.local

import com.example.pokemon_domain.models.Pokemon

fun Pokemon.toEntity(): PokemonEntity {
    return PokemonEntity(
        name = this.name,
        url = this.url
    )
}

fun PokemonEntity.toDomain(): Pokemon {
    return Pokemon(
        name = this.name,
        url = this.url
    )
}
