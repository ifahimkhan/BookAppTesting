package com.fahim.bookapptesting.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.fahim.bookapptesting.R
import com.fahim.bookapptesting.database.Book
import com.fahim.bookapptesting.databinding.FragmentBookDetailsBinding
import com.fahim.bookapptesting.util.Status
import com.fahim.bookapptesting.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookDetailsFragment @Inject constructor(private val glide: RequestManager) :
    Fragment(R.layout.fragment_book_details) {

    private lateinit var binding: FragmentBookDetailsBinding
    private lateinit var viewModel: BookViewModel
    private lateinit var callBack: OnBackPressedCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BookViewModel::class.java)

        binding = FragmentBookDetailsBinding.bind(view)

        binding.mainImageView.setOnClickListener {
            findNavController().navigate(BookDetailsFragmentDirections.actionBookDetailsFragmentToImageApiFragment())
        }
        binding.btnSave.setOnClickListener {
            String
            val book = Book(
                title = binding.etTitle.editText?.text.toString(),
                author = binding.etName.editText?.text.toString(),
                year = binding.etYear.editText?.text.toString(),
                imageUrl = ""
            )
            viewModel.validateBook(book.title, book.author, book.year)
            Log.e("TAG", "btnSave: ")
        }

        callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setSelectedImageUrl("")
                findNavController().navigateUp()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)

        viewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { url ->
            binding?.let {
                glide.load(url).into(it.mainImageView)
            }
        })
        viewModel.inserBookMessage.observe(viewLifecycleOwner, { resources ->
            when (resources.status) {
                Status.LOADING -> {
                    Toast.makeText(context, "Loading...!", Toast.LENGTH_LONG).show()
                }
                Status.ERROR -> {

                    Toast.makeText(context, "${resources.message}!", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                    viewModel.resetInsertBookMessage()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //unregister listener here
        callBack.isEnabled = false
        callBack.remove()
    }

}