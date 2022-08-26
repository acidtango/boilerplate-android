package com.example.pokemon_data.dataSource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val url: String
)
