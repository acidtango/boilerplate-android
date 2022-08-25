package com.example.pokemon_data.models

import com.squareup.moshi.Json

data class PokemonNetwork(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String
)
