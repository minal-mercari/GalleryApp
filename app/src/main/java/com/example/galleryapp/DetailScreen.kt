package com.example.galleryapp

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun DetailScreen(
    imageURL: String,
    alt_description: String?,
    user: String?,
    navController: NavController,
    imageViewModel: ImageViewModel
) {

    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Add back button as IconButton
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
        // Image
        val imageRequest = ImageRequest.Builder(context)
            .data(imageURL)
            .memoryCacheKey(imageURL)
            .diskCacheKey(imageURL)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()

        AsyncImage(
            model = imageRequest,
            contentDescription = alt_description,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .aspectRatio(1f)
        )
        // Image details
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            // Add the user details
            user?.let {
                Text(text = "AUTHOR: $user",
                    fontWeight = FontWeight.Bold
                )

            }
            // Display the alt_description if available
            alt_description?.let {
                Text(
                    text = "DESCRIPTION: $alt_description",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            // Download button
            Button(onClick = { downloadImage(context, imageURL) }) {
                Text(text = "Download Image")
            }
        }
    }
}

fun downloadImage(context: Context, imageUrl: String) {
    val request = DownloadManager.Request(Uri.parse(imageUrl))
        .setTitle("Image Download")
        .setDescription("Downloading...")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)
}



