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
        test.setOnClickListener {
            presenter.onCitiesScreen()
        }
    }
}