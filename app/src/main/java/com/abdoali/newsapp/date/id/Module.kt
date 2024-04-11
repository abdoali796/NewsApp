package com.abdoali.newsapp.date.id

import android.content.Context
import androidx.room.Room
import com.abdoali.newsapp.date.api.ApiService
import com.abdoali.newsapp.date.local.ArticleDatabase
import com.abdoali.newsapp.date.repo.Repository
import com.abdoali.newsapp.date.repo.RepositoryImp
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun apiNewsServes(): ApiService {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val okHttpClient: OkHttpClient =
            OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS).build()
        val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .baseUrl(BASE_URL).build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun databasePro(@ApplicationContext context: Context): ArticleDatabase {
        return Room.databaseBuilder(context, ArticleDatabase::class.java, "ArticleDatabase").build()
    }

    @Provides
    @Singleton
    fun reps(apiService: ApiService, database: ArticleDatabase): Repository {
        return RepositoryImp(apiService, database = database)
    }

}

private const val BASE_URL = "https://newsapi.org"