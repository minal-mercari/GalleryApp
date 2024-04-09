package com.example.galleryapp

import androidx.lifecycle.LiveData

class ImageRepository(private val apiService: UnsplashService, private val imageDao: ImageDao) {
    suspend fun fetchAndStoreImages() {
        try {
            val images = apiService.getRandomPhotos(
                query = "Fyu71pAJRs9cCKZ5-Jdl0LHqRAj7lP68SemHnHWcNu0",
                per_page = 30,
                page = 1
            )
            val imageEntities = images.map { image ->
                ImageEntity(
                    id = image.id,
                    url = image.urls.regular,
                    altDescription = image.alt_description,
                    user = image.user?.username
                )
            }
            imageDao.insertAll(imageEntities)
        } catch (e: Exception) {

        }
    }

    fun getAllImages(): LiveData<List<ImageEntity>> {
        return imageDao.getAllImages()
    }

}
