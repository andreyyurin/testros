package sad.ru.testros.custom

import android.content.Context
import android.content.SharedPreferences

class Store(val context: Context) {
    private val APP_PREFERENCES = "config"
    private val APP_USER_LOGIN = "config.login"
    private val APP_USER_PASS = "config.pass"

    private lateinit var appShared: SharedPreferences


    fun saveLogin(uid: String, id: String) {
        appShared = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = appShared.edit()
        editor.putString(APP_USER_LOGIN, id)
        editor.putString(APP_USER_PASS, uid)
        editor.apply()
    }
}