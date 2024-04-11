package com.example.galleryapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.room.Room
import com.example.galleryapp.ui.theme.UiImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageViewModel(private val repository: ImageRepository) : ViewModel() {
    val imageList: LiveData<List<UiImageModel>> = repository.getAllImages()
    init {
        fetchAndStoreImages()
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                val imageDatabase = Room.databaseBuilder(
                    application.applicationContext,
                    ImageDatabase::class.java, "image-database"
                ).build()
                val imageDao = imageDatabase.imageDao()

                return ImageViewModel(
                    ImageRepository(RetrofitClient.create(), imageDao)
                ) as T
            }
        }
    }

    private fun fetchAndStoreImages() {
        viewModelScope.launch {
            try {
        val response = withContext(Dispatchers.IO) {
            repository.fetchAndStoreImages()
                }
                Log.d("api", response.toString())
            } catch (e: Exception) {
                Log.e("ImageViewModel", "Failed to fetch and store images", e)

            }
        }
    }
}


