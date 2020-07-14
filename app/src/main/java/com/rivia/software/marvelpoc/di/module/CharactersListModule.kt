package com.rivia.software.marvelpoc.di.module

import com.rivia.software.marvelpoc.domain.api.MarvelApi
import com.rivia.software.marvelpoc.domain.api.MarvelClient
import com.rivia.software.marvelpoc.domain.repository.MarvelRepository
import com.rivia.software.marvelpoc.domain.usecase.GetCharactersUseCase
import com.rivia.software.marvelpoc.viewmodel.CharactersViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
class CharactersListModule {

    @Provides
    fun mainViewModelProvider(useCase: GetCharactersUseCase) = CharactersViewModel(useCase)

    @Provides
    fun getCharactersProvider(marvelApiRepository: MarvelRepository) =
        GetCharactersUseCase(marvelApiRepository)

    @Provides
    fun getApiRepositoryProvider(marvelApi: MarvelApi) = MarvelRepository(marvelApi)

}