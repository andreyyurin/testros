package sad.ru.testros.fragments.main

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.terrakok.cicerone.Router
import sad.ru.testros.R
import sad.ru.testros.base.BaseFragment
import sad.ru.testros.base.BasePresenter

class MainFragment : BaseFragment(), MainView {
    override fun layoutId(): Int = R.layout.fragment_main

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun isShowBottomMenu(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}