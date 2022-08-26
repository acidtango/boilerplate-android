package com.example.pokemon_presentation.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemon_presentation.components.PokemonCard
import com.example.pokemon_presentation.viewModels.PokemonsViewModel

@Composable
fun PokemonsScreen(
    onCardClick: (position: Int) -> Unit,
    viewModel: PokemonsViewModel = hiltViewModel()
) {
    LazyColumn() {
        item {
            Text("Total: " + viewModel.state.count.toString())
            Text("Cargados: " + viewModel.state.pokemonsInfo.size.toString())
        }

        itemsIndexed(viewModel.state.pokemonsInfo) { index, pokemon ->
            viewModel.pokemonScrollPosition = index
            if ((index + 1) >= viewModel.page.value) {
                viewModel.nextPage()
            }
            PokemonCard(pokemon = pokemon, position = index + 1, onClick = onCardClick)
        }
    }
}
