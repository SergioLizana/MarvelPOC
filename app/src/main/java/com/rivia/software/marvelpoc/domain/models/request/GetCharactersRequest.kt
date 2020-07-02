package com.rivia.software.marvelpoc.domain.models.request

data class GetCharactersRequest(val nameStartWith: String?, val offset: Int = 0, val limit: Int = 0)
