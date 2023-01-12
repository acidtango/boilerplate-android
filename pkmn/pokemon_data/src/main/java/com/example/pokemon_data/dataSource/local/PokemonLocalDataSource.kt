package com.example.pokemon_data.dataSource.local

import javax.inject.Inject

class PokemonLocalDataSource
@Inject constructor(private val pokemonDao: PokemonDao) {

    suspend fun insert(pokemonEntity: PokemonEntity) {
        pokemonDao.insertOrUpdate(pokemonEntity)
    }

    suspend fun getPokemons(offset: Int, limit: Int): List<PokemonEntity> {
        return pokemonDao.getPokemons(offset, limit)
    }

    suspend fun getCount(): Int {
        return pokemonDao.getCount()
    }
}
