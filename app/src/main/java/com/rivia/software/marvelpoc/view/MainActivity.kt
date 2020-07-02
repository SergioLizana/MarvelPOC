package com.rivia.software.marvelpoc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.rivia.software.marvelpoc.utils.getViewModel
import com.rivia.software.marvelpoc.R
import com.rivia.software.marvelpoc.domain.api.MarvelApi
import com.rivia.software.marvelpoc.domain.api.MarvelClient
import com.rivia.software.marvelpoc.domain.models.request.GetCharactersRequest
import com.rivia.software.marvelpoc.domain.repository.MarvelRepository
import com.rivia.software.marvelpoc.domain.usecase.GetCharactersUseCase
import com.rivia.software.marvelpoc.view.generic.MarvelViewResult
import com.rivia.software.marvelpoc.viewmodel.CharactersViewModel

class MainActivity : AppCompatActivity() {
    private val repository : MarvelRepository =
        MarvelRepository(MarvelClient().createService(MarvelApi::class.java))
    private val useCase : GetCharactersUseCase =
        GetCharactersUseCase(repository)

    private val vm: CharactersViewModel by lazy {
        getViewModel { CharactersViewModel(useCase) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm.getCharacters(GetCharactersRequest())

        vm.charactersLiveData.observe(this, Observer {
            when (it) {
                is MarvelViewResult.Success -> {
                    Log.d("test", "hello")
                }
                else -> {
                    Log.d("test", "error")
                }
            }

        })


    }
}