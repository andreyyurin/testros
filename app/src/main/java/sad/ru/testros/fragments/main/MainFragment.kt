package sad.ru.testros.fragments.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.fragment_main.*
import sad.ru.testros.R
import sad.ru.testros.base.BaseFragment
import sad.ru.testros.models.WeatherData
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : BaseFragment(), MainView {
    override fun layoutId(): Int = R.layout.fragment_main

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun isShowBottomMenu(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchListener()
    }

    @SuppressLint("SetTextI18n")
    override fun setData(data: WeatherData) {
        name.text = data.name
        date.text = getNowDate()
        temp.text = data.main.temp.toString()
        cloud.text = data.weather[0].description
        wet.text = "${data.main.humidity}%"
    }

    @SuppressLint("SimpleDateFormat")
    private fun getNowDate(): String {
        val sdf = SimpleDateFormat("dd.M.yyyy hh:mm:ss")
        return sdf.format(Date())

    }

    private fun initSearchListener() {
        search_bar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onButtonClicked(buttonCode: Int) {
            }

            override fun onSearchStateChanged(enabled: Boolean) {
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                findCity(text.toString())
                search_bar.closeSearch()
            }

        })
    }

    private fun findCity(city: String) {
        presenter.loadInfoAboutCity(city, store.getLanguage())
    }
}