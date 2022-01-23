package com.example.navfragmentdemo.loginDemo

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navfragmentdemo.R
import com.example.navfragmentdemo.databinding.FragmentLoginBinding
import com.example.navfragmentdemo.toast

class LoginFragment : Fragment() {

    companion object {
        const val LOGIN_RESULT_IS_SUCCESS = "LOGIN_RESULT_IS_SUCCESS"
        const val LOGIN_RESULT_USER_NAME = "LOGIN_RESULT_USER_NAME"
    }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        arguments?.let {
            binding.txtLabel.text = it.getString("label", "No message to display.")
        }
        addListeners()
        return binding.root
    }

    private var isPasswordVisible = false

    private fun addListeners() {
        binding.btnLogin.setOnClickListener { actionLogin() }
        binding.btnPasswordVisibility.setOnClickListener {

            binding.btnPasswordVisibility.setImageResource(if (isPasswordVisible) R.drawable.ic_invisible else R.drawable.ic_visible)
            binding.etPassword.transformationMethod = if (isPasswordVisible) PasswordTransformationMethod() else null
            isPasswordVisible = !isPasswordVisible
        }
    }

    private fun actionLogin() {
        val strUsername = binding.etUsername.text.toString().trim()
        val strPassword = binding.etPassword.text.toString().trim()
        when {
            strUsername.isNullOrBlank() -> requireActivity().toast("Please enter Username")
            strPassword.isNullOrBlank() -> requireActivity().toast("Please enter Password")
            else -> {
                findNavController().previousBackStackEntry?.savedStateHandle?.apply {
                    set(LOGIN_RESULT_IS_SUCCESS, true)
                    set(LOGIN_RESULT_USER_NAME, strUsername)
                }
                findNavController().popBackStack()
            }
        }
    }
}