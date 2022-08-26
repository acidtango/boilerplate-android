package com.example.pokemon_data.models

import com.squareup.moshi.Json

data class PokemonsNetwork(
    @field:Json(name = "count")
    val count: Int,
    @field:Json(name = "results")
    val pokemonsInfo: List<PokemonNetwork>
)
