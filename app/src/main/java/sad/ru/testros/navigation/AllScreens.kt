package sad.ru.testros.navigation

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import sad.ru.testros.fragments.cities.CitiesFragment
import sad.ru.testros.fragments.login.LoginFragment
import sad.ru.testros.fragments.main.MainFragment
import sad.ru.testros.fragments.map.MapFragment
import sad.ru.testros.fragments.register.RegisterFragment

class AllScreens {
    class LoginScreen(private val key: String) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return LoginFragment()
        }

        init {
            screenKey = key
        }
    }

    class RegisterScreen(
        private val login: String,
        private val pass: String,
        private val key: String
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return RegisterFragment.newInstance(login, pass)
        }

        init {
            screenKey = key
        }
    }

    class CitiesScreen(private val key: String) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return CitiesFragment()
        }

        init {
            screenKey = key
        }
    }

    class MainScreen(private val key: String) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return MainFragment()
        }

        init {
            screenKey = key
        }
    }

    class MapScreen(private val key: String) : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return MapFragment()
        }

        init {
            screenKey = key
        }
    }
}