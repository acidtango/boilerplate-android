package com.example.pokemon_presentation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon_domain.models.Pokemons
import com.example.pokemon_domain.useCases.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    private val pokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    var state by mutableStateOf(Pokemons(count = 0, pokemonsInfo = listOf()))
        private set

    init {
        viewModelScope.launch {
            state = pokemonsUseCase.invoke()
        }
    }
}
