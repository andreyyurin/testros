package sad.ru.testros.fragments.map

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import sad.ru.testros.R
import sad.ru.testros.base.BaseFragment

class MapFragment : BaseFragment(), MapView {
    override fun layoutId(): Int = R.layout.fragment_map

    @InjectPresenter
    lateinit var presenter: MapPresenter

    override fun isShowBottomMenu(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}