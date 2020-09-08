package sad.ru.testros.fragments.register

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import sad.ru.testros.R
import sad.ru.testros.base.BaseFragment

class RegisterFragment : BaseFragment(), RegisterView {
    override fun layoutId(): Int = R.layout.fragment_register

    @InjectPresenter
    lateinit var presenter: RegisterPresenter

    override fun isShowBottomMenu(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}