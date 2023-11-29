package app.apex.com.data.repositories

import app.apex.com.data.Post
import app.apex.com.data.remote.client.ApiClient
import retrofit2.Call

object RemoteRepository {
    //Post Api
    fun callPostApi(): Call<ArrayList<Post>> {
        return ApiClient.apiService.getPostData()
    }
}