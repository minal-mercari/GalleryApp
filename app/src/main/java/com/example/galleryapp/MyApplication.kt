package com.example.galleryapp

import android.app.Application
import androidx.room.Room

class MyApplication : Application() {

    lateinit var imageDatabase: ImageDatabase

    override fun onCreate() {
        super.onCreate()

        // Initialize Room database
        imageDatabase = Room.databaseBuilder(
            applicationContext,
            ImageDatabase::class.java, "image-database"
        ).build()
    }

    companion object {
        lateinit var instance: MyApplication
            private set

        fun getContext() = instance.applicationContext
    }
}