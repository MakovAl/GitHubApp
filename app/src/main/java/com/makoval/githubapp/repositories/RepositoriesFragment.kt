package com.makoval.githubapp.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.makoval.githubapp.databinding.RepositoriesFragmentBinding


class RepositoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RepositoriesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this


        val token = RepositoriesFragmentArgs.fromBundle(requireArguments()).token
        val viewModelFactory = RepositoriesViewModelFactory(token)

        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(RepositoriesViewModel::class.java)
        binding.repositoriesViewModel = viewModel

        binding.repositoriesList.adapter = RepositoriesAdapter(RepositoriesAdapter.OnClickListener {
            viewModel.displayRepositoryDetails(it)
        })

        viewModel.navigateToSelectedRepositories.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(RepositoriesFragmentDirections.actionRepositoriesToDetail(it, token))
                viewModel.displayRepositoryDetailsComplete()
            }
        })

        return binding.root
    }
}
