package sad.ru.testros.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import sad.ru.testros.MainApplication

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {
    protected abstract val navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        MainApplication.INSTANCE.navigatorHolder.setNavigator(navigator);
    }

    override fun onPause() {
        super.onPause()
        MainApplication.INSTANCE.navigatorHolder.removeNavigator()
    }

    protected

    abstract fun layoutId(): Int
}