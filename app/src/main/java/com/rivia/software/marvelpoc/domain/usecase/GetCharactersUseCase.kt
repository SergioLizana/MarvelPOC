package com.rivia.software.marvelpoc.domain.usecase

import com.rivia.software.marvelpoc.domain.generic.MarvelApiResult
import com.rivia.software.marvelpoc.domain.models.request.GetCharactersRequest
import com.rivia.software.marvelpoc.domain.models.response.CharacterData
import com.rivia.software.marvelpoc.domain.repository.MarvelRepository
import com.rivia.software.marvelpoc.view.data.ErrorResponse
import kotlinx.coroutines.flow.Flow

typealias GetCharacterBaseUseCase = BaseUseCase<GetCharactersRequest, Flow<MarvelApiResult<CharacterData, ErrorResponse>>>

class GetCharactersUseCase(private val marvelRepository: MarvelRepository): GetCharacterBaseUseCase {

    override suspend fun invoke(params: GetCharactersRequest): Flow<MarvelApiResult<CharacterData, ErrorResponse>> {
        return marvelRepository.getCharacters(params)
    }

}