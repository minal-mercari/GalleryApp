package com.example.galleryapp
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.galleryapp.ui.theme.GalleryappTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, ImageViewModel.Factory).get(ImageViewModel::class.java)

        Log.d("ViewModel", "ViewModel created successfully")

        setContent {
            GalleryappTheme {
                val imageViewModel: ImageViewModel = viewModel()
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(imageViewModel = imageViewModel, navController = navController)
                    }
                    composable("detail?url={param1}&alt_description={param2}&user={param3}") { backStackEntry ->

                        val name = backStackEntry.arguments?.getString("param1").toString()
                        val alt_description =
                            backStackEntry.arguments?.getString("param2").toString()
                        Log.d("image", name)
                        val userJson = backStackEntry.arguments?.getString("param3").toString()
                        Log.d(
                            "UserJson",
                            userJson
                        )
                        Log.d("v123", name.toString())

                        DetailScreen(
                           imageURL = name,
                            alt_description = alt_description,
                            user = userJson,
                            navController = navController,
                            imageViewModel = imageViewModel
                        )
                    }
                }
            }
        }
    }
}


