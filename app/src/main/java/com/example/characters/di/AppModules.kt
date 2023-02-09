package com.example.characters.di

import android.content.Context
import com.example.characters.data.CharactersRepository
import com.example.characters.data.api.CharacterRepoImpl
import com.example.characters.data.api.CharactersApi
import com.example.characters.data.local.CharactersDao
import com.example.characters.data.local.CharactersRoomDatabase
import com.example.characters.utils.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideRetrofit(
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Util.BASE_URL)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersApi(
        charactersRetrofit: Retrofit
    ): CharactersApi = charactersRetrofit.create(CharactersApi::class.java)

    @Provides
    @Singleton
    fun provideCharactersLocalDatabase(
        @ApplicationContext context: Context
    ): CharactersRoomDatabase =
        CharactersRoomDatabase
            .getDatabase(
                context,
                CoroutineScope(SupervisorJob()
            )
        )

    @Provides
    fun provideCharactersDao(
        charactersRoomDatabase: CharactersRoomDatabase
    ): CharactersDao = charactersRoomDatabase.charactersDao()

    @Provides
    @Singleton
    fun provideCharactersRepository(
        api: CharactersApi,
        dao: CharactersDao
    ): CharactersRepository = CharacterRepoImpl(api, dao)

}
