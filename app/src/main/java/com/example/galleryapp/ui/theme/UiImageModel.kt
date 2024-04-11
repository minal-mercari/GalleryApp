package com.example.galleryapp.ui.theme

import com.example.galleryapp.ImageEntity
import com.example.galleryapp.UnsplashPhoto

data class UiImageModel(
    val id: String,
    val imageUrl: String,
    val altDescription: String?,
    val user: String?
) {
    companion object {
        fun fromUnsplashPhoto(unsplashPhoto: UnsplashPhoto): UiImageModel {
            return UiImageModel(
                id = unsplashPhoto.id,
                imageUrl = unsplashPhoto.urls.regular,
                altDescription = unsplashPhoto.alt_description,
                user = unsplashPhoto.user.name
            )
        }

        fun fromImageEntity(imageEntity: ImageEntity): UiImageModel {
            return UiImageModel(
                id = imageEntity.id,
                imageUrl = imageEntity.url,
                altDescription = imageEntity.altDescription,
                user = imageEntity.user
            )
        }
    }
}
