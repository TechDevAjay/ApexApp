package app.apex.com.ui.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.apex.com.data.Photo

class PhotoViewModel : ViewModel() {

    private val TAG = PhotoViewModel::class.java.simpleName

    private val loadingState: MutableLiveData<Boolean> = MutableLiveData()
    private val mutablePhotoData: MutableLiveData<ArrayList<Photo>> = MutableLiveData()
    val photoData: LiveData<ArrayList<Photo>>
        get() = mutablePhotoData


    private fun loadDemoData() {
        val list = ArrayList<Photo>()
        for (i in 1..100) {
            val photo = Photo(
                i,
                1,
                "officia porro iure quia iusto qui ipsa ut modi",
                "https://via.placeholder.com/600/24f355",
                "https://via.placeholder.com/150/24f35"
                )
            list.add(photo)
        }
        mutablePhotoData.value = list
    }

    fun callPhotoApi() {
        loadingState.value = true
        loadDemoData()
    }
}