package com.example.galleryapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asFlow
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun HomeScreen(navController: NavController, imageViewModel: ImageViewModel) {
    val images by imageViewModel.imageList.asFlow().collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "GalleryApp",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        ImageGrid(images = images, navController = navController)

    }
}

@Composable
fun ImageGrid(images: List<ImageEntity>, navController: NavController) {
    LazyVerticalGrid(GridCells.Fixed(3)) {
        items(images) { image ->
            ImageItem(image = image, navController = navController)
        }
    }
}

@Composable
fun ImageItem(image: ImageEntity, navController: NavController) {
    val painter: Painter = rememberImagePainter(data = image.url)
    Box(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp)
            .clickable {
                // Navigate to the detail screen when the image is clicked
                println("!!!!! BEFORE ${image.url}")
                val url =
                    URLEncoder.encode(image.url, StandardCharsets.ISO_8859_1.toString())
                println("!!!!! $url")
                navController.navigate("detail?url=" + image.url + "&alt_description=" + image.altDescription + "&user=" + image.user)
            }
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}



