package sad.ru.testros.fragments.login

import com.arellomobile.mvp.InjectViewState
import sad.ru.testros.base.BasePresenter
import sad.ru.testros.base.Consts
import sad.ru.testros.navigation.AllScreens

@InjectViewState
class LoginPresenter : BasePresenter<LoginView>() {
    fun onCitiesScreen() {
        router?.replaceScreen(AllScreens.CitiesScreen(Consts.CITIES_SCREEN))
    }
}