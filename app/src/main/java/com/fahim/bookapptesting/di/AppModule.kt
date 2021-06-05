package com.fahim.bookapptesting.di

import android.content.Context
import androidx.room.Room
import com.fahim.bookapptesting.util.Constant
import com.fahim.bookapptesting.api.RetrofitAPI
import com.fahim.bookapptesting.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AppDatabase::class.java, "appDb").build()

    @Singleton
    @Provides
    fun injectBookDao(database: AppDatabase) = database.bookDao();

    @Singleton
    @Provides
    fun injectRetrofitAPI(): RetrofitAPI {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitAPI::class.java)
    }

}