package com.example.galleryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.galleryapp.ui.theme.UiImageModel

class ImageRepository(private val apiService: UnsplashService, private val imageDao: ImageDao) {
    suspend fun fetchAndStoreImages() {
        try {
            val images = apiService.getRandomPhotos(
                query = "Fyu71pAJRs9cCKZ5-Jdl0LHqRAj7lP68SemHnHWcNu0",
                per_page = 30,
                page = 1
            )
            val uiImageModels = images.map { image ->
                UiImageModel(
                    id = image.id,
                    imageUrl = image.urls.regular,
                    altDescription = image.alt_description,
                    user = image.user?.username
                )
            }
            imageDao.insertAll(uiImageModels.map { uiImageModel ->
                ImageEntity(
                    id = uiImageModel.id,
                    url = uiImageModel.imageUrl,
                    altDescription = uiImageModel.altDescription,
                    user = uiImageModel.user
                )
            })
        } catch (e: Exception) {
        }
    }

    fun getAllImages(): LiveData<List<UiImageModel>> {
        return imageDao.getAllImages().map { imageEntities ->
            imageEntities.map { imageEntity ->
                UiImageModel(
                    id = imageEntity.id,
                    imageUrl = imageEntity.url,
                    altDescription = imageEntity.altDescription,
                    user = imageEntity.user
                )
            }
        }
    }
}