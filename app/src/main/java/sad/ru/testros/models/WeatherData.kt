package sad.ru.testros.models

data class WeatherData(
    val base: String,
    val clouds: Clouds,
    val cod: Double,
    val coord: Coord,
    val dt: Double,
    val id: Double,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Double,
    val visibility: Double,
    val weather: List<Weather>,
    val wind: Wind
)

data class Clouds(
    val all: Double
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Main(
    val feels_like: Double,
    val humidity: Double,
    val pressure: Double,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Sys(
    val country: String,
    val id: Double,
    val sunrise: Double,
    val sunset: Double,
    val type: Double
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Double,
    val main: String
)

data class Wind(
    val deg: Double,
    val speed: Double
)