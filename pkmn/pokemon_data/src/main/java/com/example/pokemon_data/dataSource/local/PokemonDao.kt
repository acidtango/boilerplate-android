package com.example.pokemon_data.dataSource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonEntity)

    @Query("select * from pokemonentity where name =:name and url =:url")
    suspend fun getPokemon(name: String, url: String): PokemonEntity?

    @Update
    suspend fun update(pokemon: PokemonEntity)

    @Query("select * from pokemonentity limit :limit offset :offset")
    suspend fun getPokemons(offset: Int, limit: Int): List<PokemonEntity>

    suspend fun insertOrUpdate(
        pokemon: PokemonEntity
    ) {
        val currentPokemon = getPokemon(
            name = pokemon.name,
            url = pokemon.url
        )

        currentPokemon?.let {
            update(pokemon.copy(id = currentPokemon.id))
        } ?: insert(pokemon)
    }
}
