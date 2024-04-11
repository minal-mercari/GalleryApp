package com.example.galleryapp

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashService {
@GET("photos")
suspend fun getRandomPhotos(
    @Query("client_id") query: String,
    @Query("per_page") per_page: Int,
    @Query("page") page: Int,
    @Query("alt_description") alt_description: Boolean = true

): List<UnsplashPhoto>
@GET("users/{username}")
suspend fun getUserDetails(
    @Path("username") username: String
): User

}

