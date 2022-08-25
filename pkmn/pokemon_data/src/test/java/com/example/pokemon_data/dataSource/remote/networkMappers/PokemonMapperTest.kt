package com.example.pokemon_data.dataSource.remote.networkMappers

import com.example.pokemon_data.models.PokemonNetwork
import com.example.pokemon_data.models.PokemonsNetwork
import com.example.pokemon_domain.models.Pokemon
import com.example.pokemon_domain.models.Pokemons
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PokemonMapperTest {
    @Test
    fun `Pokemons to domain`() {
        val pokemonsNetwork = PokemonsNetwork(
            count = 2,
            pokemonsInfo = listOf(PokemonNetwork("Pikachu", ""), PokemonNetwork("Raichu", ""))
        )

        val expected = Pokemons(
            count = 2,
            pokemonsInfo = listOf(Pokemon("Pikachu", ""), Pokemon("Raichu", ""))
        )

        assertThat(pokemonsNetwork.toDomain()).isEqualTo(expected)
    }
}
