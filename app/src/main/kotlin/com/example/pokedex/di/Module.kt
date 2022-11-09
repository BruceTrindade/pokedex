package com.example.pokedex.di

import android.content.Context
import androidx.room.Room
import com.example.pokedex.api.PokemonService
import com.example.pokedex.data.local.PokedexDataBase
import com.example.pokedex.utils.Constants.BASE_URL
import com.example.pokedex.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun providePokedexDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        PokedexDataBase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideMarvelDAO(database: PokedexDataBase) = database.pokedexDao()
            /*
                @Singleton
    @Provides
    fun provideMarvelDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MarvelDataBase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideMarvelDAO(database: MarvelDataBase) = database.marvelDao()

             */

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideServiceApi(retrofit: Retrofit): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }
}
