package com.rivia.software.marvelpoc.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rivia.software.marvelpoc.databinding.CharacterListFragmentBinding
import com.rivia.software.marvelpoc.domain.models.request.GetCharactersRequest
import com.rivia.software.marvelpoc.view.generic.MarvelViewResult
import com.rivia.software.marvelpoc.viewmodel.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment: Fragment() {

    private val vm: CharactersViewModel by viewModels()
    private var _binding: CharacterListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CharacterListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}