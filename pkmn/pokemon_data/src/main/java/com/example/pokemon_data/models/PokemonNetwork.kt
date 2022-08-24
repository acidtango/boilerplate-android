package com.example.pokemon_data.models

import com.example.pokemon_domain.models.Pokemon
import com.squareup.moshi.Json

data class PokemonNetwork(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String
)
