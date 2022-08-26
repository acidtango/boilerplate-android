package com.example.pokemon_presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemon_presentation.viewModels.PokemonsViewModel

@Composable
fun PokemonDetail(
    position: Int,
    viewModel: PokemonsViewModel = hiltViewModel()
) {
    Column() {
        Text("Hello world, this would be pokemon number $position ")
    }
}
