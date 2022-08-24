package com.example.pokemon_presentation.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemon_presentation.components.PokemonCard
import com.example.pokemon_presentation.viewModels.PokemonsViewModel

@Composable
fun PokemonsScreen(
    viewModel: PokemonsViewModel = hiltViewModel()
) {
    LazyColumn() {
        item {
            Text(viewModel.state.count.toString())
        }

        itemsIndexed(viewModel.state.pokemonsInfo) { index, pokemon ->
            PokemonCard(pokemon = pokemon, position = index + 1)
        }
    }
}
