package com.rivia.software.marvelpoc.domain.repository

import com.rivia.software.marvelpoc.domain.api.MarvelApi
import com.rivia.software.marvelpoc.domain.generic.MarvelApiResult
import com.rivia.software.marvelpoc.domain.models.request.GetCharactersRequest
import com.rivia.software.marvelpoc.domain.models.response.CharacterData
import com.rivia.software.marvelpoc.utils.MarvelApiConstants.MARVEL_PUBLIC_KEY
import com.rivia.software.marvelpoc.utils.MarvelUtils.generateHash
import com.rivia.software.marvelpoc.view.data.ErrorResponse
import com.rivia.software.marvelpoc.view.generic.MarvelViewResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MarvelRepository(private val marvelAPI: MarvelApi) : IMarvelRepository{

    override suspend fun getCharacters(charactersRequest: GetCharactersRequest): Flow<MarvelApiResult<CharacterData, ErrorResponse>> {
        val ts = System.currentTimeMillis().toString()
        val response = marvelAPI.getCharacters(ts,MARVEL_PUBLIC_KEY,generateHash(ts),charactersRequest.nameStartWith,charactersRequest.offset,charactersRequest.limit)
        return flow{
            if(response.isSuccessful) {
                this.emit(MarvelApiResult.Success(response.body()))
            }else{
                this.emit(MarvelApiResult.Error(ErrorResponse(response.code(),response.message())))
            }
        }.flowOn(Dispatchers.IO)

    }
}