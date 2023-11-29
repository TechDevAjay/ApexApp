package app.apex.com.ui.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.apex.com.data.local.Post
import app.apex.com.data.remote.client.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {

    private val TAG = PostViewModel::class.java.simpleName

    val loadingState: MutableLiveData<Boolean> = MutableLiveData()
    private val mutablePostData: MutableLiveData<ArrayList<Post>> = MutableLiveData()
    val postData : LiveData<ArrayList<Post>>
        get() = mutablePostData

    private val callBackPost = object : Callback<ArrayList<Post>> {
        override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
            loadingState.value = false

            mutablePostData.value = response.body()
        }

        override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
            loadingState.value = false
            Log.d(TAG, "Failure: " + t.message)
        }
    }


    fun callPostApi() {
        loadingState.value = true
        RetrofitInstance.apiEndPoint.getPostData().enqueue(callBackPost)
    }
}