package app.apex.com.data.repositories

import app.apex.com.data.Post
import app.apex.com.data.remote.client.ApiClient
import retrofit2.Call

class RemoteRepository {

    companion object {
        private var repositoryInstance: RemoteRepository? = null
        @JvmStatic
        val instance: RemoteRepository?
            get() {
                if (repositoryInstance == null) {
                    repositoryInstance = RemoteRepository()
                }
                return repositoryInstance
            }
    }

    //Post Api
    fun callPostApi(): Call<ArrayList<Post>> {
        return ApiClient.apiService.getPostData()
    }
}