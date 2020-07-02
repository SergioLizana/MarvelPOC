package com.rivia.software.marvelpoc.view.data

import org.w3c.dom.CharacterData

data class CharacterListResponse(
    var id: Int?,
    var name: String?,
    var description: String?,
    var modified: String?,
    var imagePath: String?
)