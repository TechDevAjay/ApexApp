package app.apex.com.data.remote.client

import app.apex.com.data.local.Comment
import app.apex.com.data.local.Photo
import app.apex.com.data.local.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoint {

    //Post List Api
    @GET("posts")
    fun getPostData(): Call<ArrayList<Post>>

    //Comment Api
    @GET("comments")
    fun getPostComment(@Query("postId") postId: Int): Call<ArrayList<Comment>>

   //Photo List
    @GET("photos")
    fun getPhoto(): Call<ArrayList<Photo>>
}