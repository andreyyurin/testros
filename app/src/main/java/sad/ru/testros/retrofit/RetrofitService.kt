package sad.ru.testros.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sad.ru.testros.BuildConfig
import sad.ru.testros.models.Test

interface RetrofitService {
    @GET(BuildConfig.BASE_URL + "login.php")
    fun login(@Query("un") login: String, @Query("pwd") password: String): Call<Test>
}