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
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("publicApiKey")
    fun publicApiKeyProvider(app: MarvelApplication): String = MARVEL_PUBLIC_KEY

    @Provides
    @Singleton
    @Named("privateApiKey")
    fun privateApiKeyProvider(app: MarvelApplication): String = MARVEL_PRIVATE_KEY

  /*   @Provides
     @Singleton
     fun apiProvider(): MarvelApi =  MarvelClient().createService(MarvelApi::class.java)
*/
}