package com.rivia.software.marvelpoc.domain.models.response

import com.rivia.software.marvelpoc.domain.models.response.ItemXXX

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)