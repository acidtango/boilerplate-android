package com.example.pokemon_presentation.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.Resource
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

    var pokemonScrollPosition = 0
    val page = mutableStateOf(1)

    val limit = 10

    init {
        viewModelScope.launch {
            pokemonsUseCase().collect {
                when (it) {
                    is Resource.Error -> TODO()
                    is Resource.Success -> {
                        state = it.data!!
                    }
                }
            }
        }
    }

    fun nextPage() {
        if ((pokemonScrollPosition + 1) >= page.value && state.count > state.pokemonsInfo.size) {
            page.value = state.pokemonsInfo.size

            if (page.value > 1 && state.count >= state.pokemonsInfo.size) {
                viewModelScope.launch {
                    pokemonsUseCase.nextPage(offset = state.pokemonsInfo.size, limit = limit)
                        .collect {
                            when (it) {
                                is Resource.Error -> TODO()
                                is Resource.Success -> {
                                    val originalList = state.pokemonsInfo
                                    val newList = it.data!!.pokemonsInfo
                                    val combinedList = originalList.union(newList).toList()
                                    state = state.copy(
                                        count = it.data!!.count,
                                        pokemonsInfo = combinedList
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}
