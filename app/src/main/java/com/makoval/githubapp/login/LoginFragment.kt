package com.makoval.githubapp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.makoval.githubapp.R
import com.makoval.githubapp.databinding.LoginFragmentBinding


class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LoginFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.personalAccessToken.setOnFocusChangeListener { view, b ->
            binding.textPersonalAccessToken.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.blue
                )
            )
            DrawableCompat.setTint(
                view.background,
                ContextCompat.getColor(binding.root.context, R.color.blue)
            )
            binding.textPersonalAccessToken.visibility = View.VISIBLE
        }

        binding.login.setOnClickListener {
            if (!binding.personalAccessToken.text.isNullOrEmpty()) {
                val viewModel = LoginViewModel(binding.personalAccessToken.text.toString())
                binding.viewModel = viewModel
            }
        }
        return binding.root
    }
}
