package com.example.pokemon_domain.di

import com.example.pokemon_domain.repository.PokemonRepository
import com.example.pokemon_domain.useCases.GetPokemonsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // TODO change this to viewmodel
object PokemonDomainModule {

    // TODO change this to viewmodel also
    @Provides
    @Singleton
    fun providesPokemonUseCase(
        repository: PokemonRepository
    ): GetPokemonsUseCase {
        return GetPokemonsUseCase(repository)
    }
}
