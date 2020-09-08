package sad.ru.testros.fragments.register

import com.arellomobile.mvp.InjectViewState
import sad.ru.testros.base.BasePresenter
import sad.ru.testros.navigation.AllScreens

@InjectViewState
class RegisterPresenter : BasePresenter<RegisterView>() {

    fun onLogin() {
        router?.replaceScreen(AllScreens.LoginScreen(""))
    }
}