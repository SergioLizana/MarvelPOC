package com.rivia.software.marvelpoc.di.module

import com.rivia.software.marvelpoc.app.MarvelApplication
import com.rivia.software.marvelpoc.domain.api.MarvelApi
import com.rivia.software.marvelpoc.domain.api.MarvelClient

import com.rivia.software.marvelpoc.utils.MarvelApiConstants.MARVEL_PRIVATE_KEY
import com.rivia.software.marvelpoc.utils.MarvelApiConstants.MARVEL_PUBLIC_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(marvelClient: MarvelClient): MarvelApi = marvelClient.createService(MarvelApi::class.java)

    @Provides
    @Singleton
    fun provideClient(): MarvelClient = MarvelClient()
}