package sad.ru.testros.fragments.login

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_login.*
import sad.ru.testros.R
import sad.ru.testros.base.BaseFragment

class LoginFragment : BaseFragment(), LoginView {
    override fun layoutId(): Int = R.layout.fragment_login

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun isShowBottomMenu(): Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEnterListener()
    }

    private fun initEnterListener() {
        btn_enter.setOnClickListener {
            checkLogin()
        }
    }

    private fun checkLogin() {
        if (et_login.text.toString().isEmpty() || et_password.text.toString().isEmpty()) {
            showToast("Заполните все поля")
            return
        }

        if (store.getPassword(et_login.text.toString()) == et_password.text.toString()) {
            openMainScreen()
        } else if (store.getPassword(et_login.text.toString()).isNotEmpty()) {
            showToast("Введен неправильный пароль")
        } else {
            openRegisterScreen(et_login.text.toString(), et_password.text.toString())
        }
    }

    private fun openRegisterScreen(login: String, pass: String) {
        presenter.onRegisterScreen(login, pass)
    }

    private fun openMainScreen() {
        presenter.onMainScreen()
    }
}