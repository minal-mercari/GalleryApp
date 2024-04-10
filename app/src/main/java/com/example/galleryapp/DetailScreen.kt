package com.example.galleryapp

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun DetailScreen(
    imageURL: String,
    alt_description: String?,
    user: String?,
    navController: NavController,
    imageViewModel: ImageViewModel
) {

    val context = LocalContext.current
    val url = URLDecoder.decode(imageURL, StandardCharsets.ISO_8859_1.toString())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Detail Screen", modifier = Modifier.padding(bottom = 16.dp))


        val imageRequest = ImageRequest.Builder(context)
            .data(url)
            .memoryCacheKey(url)
            .diskCacheKey(url)
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
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            // Add the user details
            user?.let {
                Text(text = "Author: $user")
            }

            // Display the alt_description if available
            alt_description?.let {
                Text(
                    text = "Alt description: $alt_description",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Download button
            Button(onClick = { downloadImage(context, imageURL) }) {
                Text(text = "Download Image")
            }
        }

        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Go back")
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



