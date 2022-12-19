package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false
        )

        loginOnClickListener()
        registerOnClickListener()

        return binding.root
    }

    private fun loginOnClickListener() {
        binding.loginButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }
    }

    private fun registerOnClickListener() {
        var currentState : Boolean = false
        binding.registerLink.setOnClickListener {
            when (currentState) {
               false -> {
                    binding.registerText.setText(R.string.register_link_text)
                    binding.registerLink.setText(R.string.register_link)
                    binding.loginTitle.setText(R.string.login_text)
                    binding.loginButton.setText(R.string.login_text)
                    currentState = true
                }
                else -> {
                    binding.registerText.setText(R.string.login_link_text)
                    binding.registerLink.setText(R.string.login_link)
                    binding.loginTitle.setText(R.string.register_text)
                    binding.loginButton.setText(R.string.register_text)
                    currentState = false
                }
            }
        }
    }
}