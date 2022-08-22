package com.example.pokemon_data.models

import com.squareup.moshi.Json

data class Pokemon(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String
)
