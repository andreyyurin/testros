package sad.ru.testros.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sad.ru.testros.BuildConfig.BASE_URL

object RetrofitClientInstance {
    private var retrofit: Retrofit? = null

    val client = OkHttpClient.Builder().build()

    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit
        }
}