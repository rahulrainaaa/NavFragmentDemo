package com.example.navfragmentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navfragmentdemo.databinding.FragmentDeepLinkTest1Binding

class DeepLinkTest1Fragment : Fragment() {

    private lateinit var binding: FragmentDeepLinkTest1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDeepLinkTest1Binding.inflate(inflater, container, false)

        arguments?.let {
            val sno = it.getInt("sno")
            val name = it.getString("name")
            val isActive = it.getBoolean("isActive")

            val stringBuilder = StringBuilder()
                .append("Deep Link Test 1 Fragment\n")
                .append("sno = $sno \n")
                .append("name = $name \n")
                .append("isActive = $isActive")

            binding.txtLabel.text = stringBuilder.toString()
        }

        binding.btnDone.setOnClickListener {
            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set("result1", "This is deeplink test 1 fragment's result String... !!!")
            navController.popBackStack()
        }

        return binding.root
    }
}