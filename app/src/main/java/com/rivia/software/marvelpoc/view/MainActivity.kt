package com.rivia.software.marvelpoc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.rivia.software.marvelpoc.utils.getViewModel
import com.rivia.software.marvelpoc.R
import com.rivia.software.marvelpoc.databinding.ActivityMainBinding
import com.rivia.software.marvelpoc.domain.api.MarvelApi
import com.rivia.software.marvelpoc.domain.api.MarvelClient
import com.rivia.software.marvelpoc.domain.models.request.GetCharactersRequest
import com.rivia.software.marvelpoc.domain.repository.MarvelRepository
import com.rivia.software.marvelpoc.domain.usecase.GetCharactersUseCase
import com.rivia.software.marvelpoc.view.generic.MarvelViewResult
import com.rivia.software.marvelpoc.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MarvelPOC)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}