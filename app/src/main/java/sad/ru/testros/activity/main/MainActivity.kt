package sad.ru.testros.activity.main

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import sad.ru.testros.MainNavigator
import sad.ru.testros.R
import sad.ru.testros.base.BaseActivity
import sad.ru.testros.custom.Store


class MainActivity : BaseActivity(), MainActView {
    @InjectPresenter
    internal lateinit var presenter: MainActPresenter

    override lateinit var navigator: Navigator

    lateinit var store: Store

    override fun layoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStore()
        initLocale()

        initNavigator()
        initBottomNavigationView(savedInstanceState)

        openFirstScreen()
    }

    private fun initStore() {
        store = Store(this)
    }

    private fun initLocale() {
        val resources: Resources = resources
        val configuration: Configuration = resources.configuration

        when {
            configuration.locale.toLanguageTag().trim().toLowerCase().contains("ru") -> {
                store.saveLanguage("ru")
            }
            configuration.locale.toLanguageTag().trim().toLowerCase().contains("en") -> {
                store.saveLanguage("en")
            }
            configuration.locale.toLanguageTag().trim().toLowerCase().contains("fr") -> {
                store.saveLanguage("fr")
            }
            else -> {
                store.saveLanguage("ru")
            }
        }
    }

    override fun showBottomMenu(show: Boolean) {
        if (!show) main_bottom_navigation.visibility = View.GONE
        else main_bottom_navigation.visibility = View.VISIBLE
    }

    private fun initBottomNavigationView(savedInstanceState: Bundle?) {
        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_action_main -> presenter.onMainScreen()
                R.id.bottom_nav_action_map -> presenter.onMapScreen()
                R.id.bottom_nav_action_cities -> presenter.onCitiesScreen()
            }
            true
        }

        savedInstanceState ?: apply {
            main_bottom_navigation.selectedItemId = R.id.bottom_nav_action_main
        }
    }

    private fun initNavigator() {
        navigator = MainNavigator(
            this, R.id.main_fragment_container
        )
    }

    private fun openFirstScreen() {
        presenter.onLoginScreen()
    }
}
