package com.rivia.software.marvelpoc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivia.software.marvelpoc.domain.generic.MarvelApiResult
import com.rivia.software.marvelpoc.domain.models.request.GetCharactersRequest
import com.rivia.software.marvelpoc.domain.usecase.GetCharactersUseCase
import com.rivia.software.marvelpoc.view.data.CharacterListResponse
import com.rivia.software.marvelpoc.view.data.ErrorResponse
import com.rivia.software.marvelpoc.view.generic.MarvelViewResult
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.w3c.dom.CharacterData

class CharactersViewModel(val getCharactersUseCase: GetCharactersUseCase): ViewModel() {

    val charactersLiveData: LiveData<MarvelViewResult<List<CharacterListResponse>,ErrorResponse>>
        get() = _getCharactersViewState

    private var _getCharactersViewState = MutableLiveData<MarvelViewResult<List<CharacterListResponse>,ErrorResponse>>()

    fun getCharacters(request: GetCharactersRequest) {
        _getCharactersViewState.value = MarvelViewResult.Loading
        viewModelScope.launch {
           getCharactersUseCase.invoke(request).collect { apiResult ->
               when(apiResult){
                   is MarvelApiResult.Error -> {
                       _getCharactersViewState.value = MarvelViewResult.Error(apiResult.error)
                   }
                   is MarvelApiResult.Success -> {
                       _getCharactersViewState.value = MarvelViewResult.Success(apiResult.data?.data?.results?.map {
                           CharacterListResponse(it.id,it.name,it.description,it.modified,it.thumbnail.path)
                       })
                   }
               }

           }
        }

    }


}