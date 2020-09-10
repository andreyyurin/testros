package sad.ru.testros.fragments.register

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_register.*
import sad.ru.testros.R
import sad.ru.testros.base.BaseFragment

class RegisterFragment : BaseFragment(), RegisterView {

    companion object {
        private const val ARGS_REG_LOGIN = "RegisterFragment.login"
        private const val ARGS_REG_PASSWORD = "ShopFragment.password"

        fun newInstance(login: String, pass: String): RegisterFragment {
            val fragment = RegisterFragment()

            val args = Bundle()
            args.putString(ARGS_REG_LOGIN, login)
            args.putString(ARGS_REG_PASSWORD, pass)
            fragment.arguments = args

            return fragment
        }
    }

    override fun layoutId(): Int = R.layout.fragment_register

    @InjectPresenter
    lateinit var presenter: RegisterPresenter

    override fun isShowBottomMenu(): Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initRegisterListener()
    }

    private fun initData() {
        val login = arguments!!.getString(ARGS_REG_LOGIN)!!
        val password = arguments!!.getString(ARGS_REG_PASSWORD)!!

        et_password.setText(password)
        et_login.setText(login)
    }

    private fun initRegisterListener() {
        btn_register.setOnClickListener {
            checkRegister()
        }
    }

    private fun checkRegister() {
        if (et_login.text.toString().isEmpty()
            || et_password.text.toString().isEmpty()
            || et_repassword.text.toString().isEmpty()
            || et_name.text.toString().isEmpty()
        ) {
            showToast("Заполните все поля")
            return
        }

        if (et_password.text.toString() != (et_repassword.text.toString())) {
            showToast("Пароли не совпадают")
            return
        }

        if (store.getPassword(et_login.text.toString()).isNotEmpty()) {
            showToast("Данный пользователь уже существует")
            return
        }

        saveData()
        openMainScreen()
    }

    private fun saveData() {
        store.savePassword(et_login.text.toString(), et_password.text.toString())
        store.saveName(et_login.text.toString(), et_name.text.toString())
    }

    private fun openMainScreen() {
        presenter.onMainScreen()
    }
}