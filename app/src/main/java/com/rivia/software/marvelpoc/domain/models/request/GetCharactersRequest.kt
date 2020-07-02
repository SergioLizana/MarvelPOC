package com.rivia.software.marvelpoc.domain.models.request

data class GetCharactersRequest(val nameStartWith: String? = null, val offset: Int? = null, val limit: Int? = null)
