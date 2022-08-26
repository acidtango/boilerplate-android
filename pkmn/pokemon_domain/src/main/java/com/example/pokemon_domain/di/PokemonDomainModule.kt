package com.example.pokemon_domain.di

import com.example.pokemon_domain.repository.PokemonRepository
import com.example.pokemon_domain.useCases.GetPokemonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PokemonDomainModule {

    @ViewModelScoped
    @Provides
    fun providesPokemonUseCase(
        repository: PokemonRepository
    ): GetPokemonsUseCase {
        return GetPokemonsUseCase(repository)
    }
}
