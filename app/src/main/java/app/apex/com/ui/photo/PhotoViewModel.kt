package app.apex.com.ui.photo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.apex.com.data.Photo
import app.apex.com.data.remote.client.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoViewModel : ViewModel() {

    private val TAG = PhotoViewModel::class.java.simpleName

    val loadingState: MutableLiveData<Boolean> = MutableLiveData()
    private val mutablePhotoData: MutableLiveData<ArrayList<Photo>> = MutableLiveData()
    val photoData: LiveData<ArrayList<Photo>>
        get() = mutablePhotoData

    private val callBackPhoto = object : Callback<ArrayList<Photo>> {
        override fun onResponse(
            call: Call<ArrayList<Photo>>,
            response: Response<ArrayList<Photo>>
        ) {
            loadingState.value = false

            mutablePhotoData.value = response.body()
        }

        override fun onFailure(call: Call<ArrayList<Photo>>, t: Throwable) {
            loadingState.value = false
            Log.d(TAG, "Failure: " + t.message)
        }

    }

    fun callPhotoApi() {
        loadingState.value = true
        RetrofitInstance.apiEndPoint.getPhoto().enqueue(callBackPhoto)
    }
}