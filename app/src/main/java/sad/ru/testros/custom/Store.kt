package sad.ru.testros.custom

import android.content.Context
import android.content.SharedPreferences

class Store(val context: Context) {
    private val APP_PREFERENCES = "config"
    private val APP_USER_LOGIN = "config.login"
    private val APP_USER_NAME = "config.name"
    private val APP_USER_PASS = "config.pass"
    private val APP_USER_LANG = "config.lang"

    private lateinit var appShared: SharedPreferences

    fun saveLanguage(lang: String) {
        appShared = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = appShared.edit()
        editor.putString(APP_USER_LANG, lang)
        editor.apply()
    }

    fun savePassword(login: String, password: String) {
        appShared = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = appShared.edit()
        editor.putString(APP_USER_PASS + login, password)
        editor.apply()
    }

    fun saveName(login: String, name: String) {
        appShared = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = appShared.edit()
        editor.putString(APP_USER_NAME + login, name)
        editor.apply()
    }


    fun getLanguage(): String {
        appShared = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        return appShared.getString(APP_USER_LANG, "ru")!!
    }

    fun getPassword(login: String): String {
        appShared = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        return appShared.getString(APP_USER_PASS + login, "")!!
    }
}