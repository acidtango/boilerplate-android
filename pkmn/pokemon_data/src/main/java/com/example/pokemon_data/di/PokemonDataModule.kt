package com.example.pokemon_data.di

import android.app.Application
import androidx.room.Room
import com.example.pokemon_data.dataSource.local.PokemonDao
import com.example.pokemon_data.dataSource.local.PokemonDatabase
import com.example.pokemon_data.dataSource.local.PokemonLocalDataSource
import com.example.pokemon_data.dataSource.remote.PokemonApi
import com.example.pokemon_data.dataSource.remote.PokemonNetworkDataSource
import com.example.pokemon_data.repository.PokemonRepositoryImpl
import com.example.pokemon_domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonDataModule {

    @Singleton
    @Provides
    fun providePokemonApi(): PokemonApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
            )
            .build()
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonDatabase(app: Application): PokemonDatabase {
        return Room.databaseBuilder(
            app,
            PokemonDatabase::class.java,
            "pokemon_db"
        ).build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(database: PokemonDatabase): PokemonDao {
        return database.pokemonDao()
    }

    @Singleton
    @Provides
    fun providePokemonNetworkDataSource(api: PokemonApi): PokemonNetworkDataSource {
        return PokemonNetworkDataSource(
            api = api
        )
    }

    @Singleton
    @Provides
    fun provideLocalPokemonDataSource(pokemonDao: PokemonDao): PokemonLocalDataSource {
        return PokemonLocalDataSource(
            pokemonDao = pokemonDao
        )
    }

    @Singleton
    @Provides
    fun providePokemonRepository(
        network: PokemonNetworkDataSource,
        local: PokemonLocalDataSource
    ): PokemonRepository {
        return PokemonRepositoryImpl(
            network = network, local = local
        )
    }
}
