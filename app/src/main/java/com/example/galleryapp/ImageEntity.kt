package com.example.galleryapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey val id: String,
    val url: String,
    val altDescription: String?,
    val user: String?
)