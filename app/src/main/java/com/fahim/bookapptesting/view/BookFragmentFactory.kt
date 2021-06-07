package com.fahim.bookapptesting.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.fahim.bookapptesting.view.adapter.BookRecyclerAdapter
import com.fahim.bookapptesting.view.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class BookFragmentFactory @Inject constructor(
    private val glide: RequestManager,
    private val adapter: BookRecyclerAdapter,
    private val imageRecyclerAdapter: ImageRecyclerAdapter
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            BookDetailsFragment::class.java.name -> BookDetailsFragment(glide)
            BookFragment::class.java.name -> BookFragment(adapter)
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }


    }
}