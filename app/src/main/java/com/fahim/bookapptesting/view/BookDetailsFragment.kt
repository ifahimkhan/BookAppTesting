package com.fahim.bookapptesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.fahim.bookapptesting.R
import com.fahim.bookapptesting.databinding.FragmentBookDetailsBinding

class BookDetailsFragment : Fragment(R.layout.fragment_book_details) {

    private lateinit var binding: FragmentBookDetailsBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBookDetailsBinding.bind(view)
        navController = findNavController()

        binding.imageView.setOnClickListener {
            navController.navigate(BookDetailsFragmentDirections.actionBookDetailsFragmentToImageApiFragment())
        }
    }
}