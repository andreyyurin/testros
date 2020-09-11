package sad.ru.testros.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sad.ru.testros.BuildConfig
import sad.ru.testros.models.WeatherData

interface RetrofitService {
    @GET(BuildConfig.BASE_URL + "?appid=" + BuildConfig.BASE_API_KEY + "&units=metric")
    fun findCityWeather(@Query("q") city: String, @Query("lang") lang: String): Call<WeatherData>
}