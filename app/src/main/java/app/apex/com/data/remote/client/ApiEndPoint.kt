package app.apex.com.data.remote.client

import app.apex.com.data.Comment
import app.apex.com.data.Photo
import app.apex.com.data.Post
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndPoint {

    //Post List Api
    @GET("posts")
    fun getPostData(): Call<ArrayList<Post>>

    //Comment Api
    @POST("comments")
    fun getPostComment(@Field ("postId") postId: Int): Call<ArrayList<Comment>>

   //Photo List
    @GET("photos")
    fun getPhoto(): Call<ArrayList<Photo>>
}