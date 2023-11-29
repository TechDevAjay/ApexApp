package app.apex.com.ui.comments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.apex.com.data.local.Comment
import app.apex.com.data.remote.client.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentViewModel: ViewModel() {

    private val TAG = CommentViewModel::class.java.simpleName

    val loadingState: MutableLiveData<Boolean> = MutableLiveData()
    private val mutableCommentData: MutableLiveData<ArrayList<Comment>> = MutableLiveData()
    val commentData: LiveData<ArrayList<Comment>>
        get() = mutableCommentData

    private val callBackComment = object : Callback<ArrayList<Comment>> {
        override fun onResponse(
            call: Call<ArrayList<Comment>>,
            response: Response<ArrayList<Comment>>
        ) {
            loadingState.value = false
            mutableCommentData.value = response.body()
        }

        override fun onFailure(call: Call<ArrayList<Comment>>, t: Throwable) {
            loadingState.value = false
            Log.d(TAG, "Failure: " + t.message)
        }

    }

    fun callCommentApi(postId: Int) {
        loadingState.value = true
        RetrofitInstance.apiEndPoint.getPostComment(postId).enqueue(callBackComment)
    }
}