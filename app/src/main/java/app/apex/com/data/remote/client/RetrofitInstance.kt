package app.apex.com.data.remote.client

import app.apex.com.data.constants.AppConstant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstant.APEX_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiEndPoint: ApiEndPoint by lazy {
        retrofit.create(ApiEndPoint::class.java)
    }
}