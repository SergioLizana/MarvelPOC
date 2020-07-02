package com.rivia.software.marvelpoc.domain.repository

import com.rivia.software.marvelpoc.domain.generic.MarvelApiResult
import com.rivia.software.marvelpoc.domain.models.request.GetCharactersRequest
import com.rivia.software.marvelpoc.domain.models.response.CharacterData
import com.rivia.software.marvelpoc.view.data.ErrorResponse
import com.rivia.software.marvelpoc.view.generic.MarvelViewResult
import kotlinx.coroutines.flow.Flow


interface IMarvelRepository {
    suspend fun getCharacters(charactersRequest: GetCharactersRequest): Flow<MarvelApiResult<CharacterData, ErrorResponse>>
}