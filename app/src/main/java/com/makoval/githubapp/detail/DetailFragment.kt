package com.makoval.githubapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.makoval.githubapp.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val repos = DetailFragmentArgs.fromBundle(requireArguments()).repositories
        val token = DetailFragmentArgs.fromBundle(requireArguments()).token
        val viewModelFactory = DetailViewModelFactory(repos, token)

        binding.viewModelDetail =
            ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        return binding.root
    }
}
