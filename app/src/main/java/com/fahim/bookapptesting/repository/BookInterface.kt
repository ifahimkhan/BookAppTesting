package com.fahim.bookapptesting.repository

import androidx.lifecycle.LiveData
import com.fahim.bookapptesting.database.Book
import com.fahim.bookapptesting.model.ImageResponse
import com.fahim.bookapptesting.util.Resource

interface BookInterface {
    suspend fun insertBook(book: Book)
    suspend fun deleteBook(book: Book)
    suspend fun updateBook(book: Book)
    suspend fun searchBook(image: String): Resource<ImageResponse>
    fun getBooks(): LiveData<List<Book>>
}