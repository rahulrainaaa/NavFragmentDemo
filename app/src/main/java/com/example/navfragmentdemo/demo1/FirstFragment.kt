package com.example.navfragmentdemo.demo1

import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navfragmentdemo.R
import com.example.navfragmentdemo.databinding.FragmentFirstBinding
import com.example.navfragmentdemo.toast

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment())
        }

        binding.btnDeepLink1.setOnClickListener {
            findNavController().navigate(Uri.parse("app://deeplink.test1/22/FirstFrag/true"))
        }
    }

    override fun onResume() {
        super.onResume()

        val result1 = findNavController().currentBackStackEntry?.savedStateHandle?.get<String>("result1")
        binding.textviewNavResultData.text = result1
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_first_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu_1 -> requireActivity().toast("Selected: Menu Item 1")
            R.id.action_menu_2 -> requireActivity().toast("Selected: Menu Item 2")
            R.id.action_menu_3 -> requireActivity().toast("Selected: Menu Item 3")
        }
        return super.onOptionsItemSelected(item)
    }

}