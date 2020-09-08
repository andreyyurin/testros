package sad.ru.testros.activity.main

import com.arellomobile.mvp.InjectViewState
import sad.ru.testros.base.BasePresenter
import sad.ru.testros.base.Consts
import sad.ru.testros.navigation.AllScreens

@InjectViewState
class MainActPresenter : BasePresenter<MainActView>() {
    fun onLoginScreen() {
        router?.replaceScreen(AllScreens.LoginScreen(Consts.LOGIN_SCREEN))
    }

    fun onMainScreen() {
        router?.replaceScreen(AllScreens.MainScreen(Consts.MAIN_SCREEN))
    }

    fun onCitiesScreen() {
        router?.replaceScreen(AllScreens.CitiesScreen(Consts.CITIES_SCREEN))
    }

    fun onMapScreen() {
        router?.replaceScreen(AllScreens.MapScreen(Consts.MAP_SCREEN))
    }
}