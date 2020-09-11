package sad.ru.testros.fragments.main

import sad.ru.testros.base.BaseView
import sad.ru.testros.models.WeatherData

interface MainView : BaseView {
    fun setData(data: WeatherData)
}