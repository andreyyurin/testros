package sad.ru.testros

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class MainApplication : android.app.Application() {

    companion object {
        lateinit var INSTANCE: MainApplication
    }

    private val cicerone = Cicerone.create()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    val navigatorHolder: NavigatorHolder = cicerone.navigatorHolder

    val router: Router = cicerone.router
}
