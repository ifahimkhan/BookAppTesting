package com.fahim.bookapptesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.fahim.bookapptesting.R
import com.fahim.bookapptesting.databinding.FragmentMainBinding

class BookFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view);
        navController = findNavController()

        binding.floatingActionButton.setOnClickListener {
            navController.navigate(BookFragmentDirections.actionBookFragmentToBookDetailsFragment())
        }
    }


}