package com.example.galleryapp

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.unsplash.com/"
    private const val ACCESS_KEY = "Fyu71pAJRs9cCKZ5-Jdl0LHqRAj7lP68SemHnHWcNu0"

    fun create(): UnsplashService {
               val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            Log.d("api","handiling the api issue")

            return retrofit.create(UnsplashService::class.java)

        }


    }

