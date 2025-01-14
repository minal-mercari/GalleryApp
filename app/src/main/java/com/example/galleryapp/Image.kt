package com.example.galleryapp

data class UnsplashPhoto(
    val id: String,
    val urls: UnsplashUrls,
    val alt_description:String,
    val accepted_tos: Boolean,
    val bio: String?,
    val first_name: String?,
    val for_hire: Boolean,
    val user: User

)

data class UnsplashUrls(
    val regular: String
)

data class User(
    val accepted_tos: Boolean,
    val bio: String?,
    val first_name: String?,
    val for_hire: Boolean,
    val id: String?,
    val instagram_username: String?,
    val last_name: String?,
    val location: String?,
    val name: String?,
    val portfolio_url: String?,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String?,
    val updated_at: String?,
    val username: String?
)

