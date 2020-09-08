package sad.ru.testros.fragments.cities

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import sad.ru.testros.R
import sad.ru.testros.base.BaseFragment

class CitiesFragment : BaseFragment(), CitiesView {
    override fun layoutId(): Int = R.layout.fragment_cities

    @InjectPresenter
    lateinit var presenter: CitiesPresenter

    override fun isShowBottomMenu(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}