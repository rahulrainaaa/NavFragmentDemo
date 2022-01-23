package com.example.navfragmentdemo.loginDemo

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navfragmentdemo.databinding.FragmentWelcomeBinding
import com.example.navfragmentdemo.toast

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        addListeners()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        findNavController().currentBackStackEntry?.savedStateHandle?.run {

            if (get<Boolean>(LoginFragment.LOGIN_RESULT_IS_SUCCESS) == true) {
                requireActivity().toast("Successfully Logged in")
                val username = get<String>(LoginFragment.LOGIN_RESULT_USER_NAME)
                binding.txtLabel.text = "Welcome \"$username\""
            } else {
                binding.txtLabel.text = "Need your login to process."
            }
        }
    }

    private fun addListeners() {

        binding.btnLogin.setOnClickListener {
            val strLabel = "Login to access your account details."
            findNavController().navigate(Uri.parse("test://demo.login/$strLabel"))
        }
    }

}