package sad.ru.testros.fragments.main

import com.arellomobile.mvp.InjectViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sad.ru.testros.base.BasePresenter


@InjectViewState
class MainPresenter : BasePresenter<MainView>() {
    fun loadInfoAboutCity(city: String, lang: String) {
        CoroutineScope(uiContext + withExceptionHandler()).launch {
            loadData(service.findCityWeather(city, lang), viewState::setData)
        }
    }
}