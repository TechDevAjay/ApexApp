package app.apex.com.data.remote.client

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
//    val apiService: ApiEndPoint by lazy {
//        RetrofitClient.retrofit.create(ApiEndPoint::class.java)
//    }
//
//
//object RetrofitClient {
//    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
//
//    val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
    private const val TIMEOUT_PERIOD: Int = 120
    private var apiEndpoint: ApiEndPoint? = null

    fun getAuthApi(): ApiEndPoint? {
        if (apiEndpoint == null) {
            val retrofit: Retrofit = getHeaderRetrofitInstance()
            apiEndpoint = retrofit.create(ApiEndPoint::class.java)
        }
        return apiEndpoint
    }

    private fun getHeaderRetrofitInstance(): Retrofit {
        val baseUrl = "https://jsonplaceholder.typicode.com/"
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getNormalOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun getNormalOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(
                ApiClient.TIMEOUT_PERIOD.toLong(),
                TimeUnit.SECONDS
            ) //Sets the default read timeout for new connections
            .writeTimeout(
                ApiClient.TIMEOUT_PERIOD.toLong(),
                TimeUnit.SECONDS
            ) //set the default write timeout for new connections
            .connectTimeout(
                ApiClient.TIMEOUT_PERIOD.toLong(),
                TimeUnit.SECONDS
            ) //Sets the default connect timeout for new connections.

        okHttpClientBuilder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            // Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        })

        return okHttpClientBuilder.build()
    }
}
