package com.example.pokemon_presentation.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemon_presentation.viewModels.PokemonsViewModel

@Composable
fun PokemonsScreen(
    viewModel: PokemonsViewModel = hiltViewModel()
) {
    LazyColumn() {
        item {
            Text(viewModel.state.count.toString())
        }

        items(viewModel.state.pokemonsInfo) { pokemon ->
            Text(text = pokemon.name.capitalize(Locale.current))
            // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png
        }
    }
}
