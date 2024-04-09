
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.galleryapp.ImageDatabase
import com.example.galleryapp.ImageRepository
import com.example.galleryapp.MyApplication
import com.example.galleryapp.RetrofitClient

class MyViewModel(
    private val myRepository: ImageRepository,
    private val imageDatabase: ImageDatabase
) : ViewModel() {

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])

                // Access imageDatabase from MyApplication instance
                val imageDatabase = (application as MyApplication).imageDatabase

                // Retrieve the imageDao
                val imageDao = imageDatabase.imageDao()

                return MyViewModel(
                    ImageRepository(RetrofitClient.create(), imageDao),
                    imageDatabase
                ) as T
            }
        }
    }
}