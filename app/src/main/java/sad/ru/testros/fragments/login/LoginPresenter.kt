package sad.ru.testros.fragments.login

import com.arellomobile.mvp.InjectViewState
import sad.ru.testros.base.BasePresenter
import sad.ru.testros.base.Consts
import sad.ru.testros.navigation.AllScreens

@InjectViewState
class LoginPresenter : BasePresenter<LoginView>() {
    fun onMainScreen() {
        router?.replaceScreen(AllScreens.MainScreen(Consts.MAIN_SCREEN))
    }

    fun onRegisterScreen(login: String, pass: String) {
        router?.navigateTo(AllScreens.RegisterScreen(login, pass, Consts.REGISTER_SCREEN))
    }
}