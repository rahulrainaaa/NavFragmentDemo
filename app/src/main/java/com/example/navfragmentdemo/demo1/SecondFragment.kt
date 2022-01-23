package com.example.navfragmentdemo.demo1

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navfragmentdemo.R
import com.example.navfragmentdemo.databinding.FragmentSecondBinding
import com.example.navfragmentdemo.toast

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonDeepLink.setOnClickListener {
            findNavController().navigate(Uri.parse("app://deeplink.test1/31/SecondFrag/false"))
        }
    }

    override fun onResume() {
        super.onResume()
        val result1 = findNavController().currentBackStackEntry?.savedStateHandle?.get<String>("result1")
        binding.textviewSecond.text = "Second Fragment\n $result1"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_second_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu_4 -> requireActivity().toast("Selected: Menu Item 4")
            R.id.action_menu_5 -> requireActivity().toast("Selected: Menu Item 5")
            R.id.action_menu_6 -> requireActivity().toast("Selected: Menu Item 6")
        }
        return super.onOptionsItemSelected(item)
    }
}