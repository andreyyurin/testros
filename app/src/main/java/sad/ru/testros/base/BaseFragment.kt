package sad.ru.testros.base

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import sad.ru.testros.custom.Store
import sad.ru.testros.db.HistoryDbHelper
import sad.ru.testros.retrofit.RetrofitClientInstance
import sad.ru.testros.retrofit.RetrofitService

abstract class BaseFragment() :
    MvpAppCompatFragment(), BaseView {

    lateinit var service: RetrofitService
    lateinit var store: Store
    lateinit var dbHelper: HistoryDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initService()
        initStore()
        initDbHelper()
    }

    private fun initDbHelper() {
        dbHelper = HistoryDbHelper(this.requireContext(), null)
    }

    private fun initService() {
        service =
            RetrofitClientInstance.retrofitInstance!!.create(RetrofitService::class.java)
    }

    private fun initStore() {
        store = Store(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    fun showToast(text: String) {
        Toast.makeText(this.requireContext(), text, Toast.LENGTH_LONG).show()
    }

    fun convertDpToPixel(dp: Float, context: Context?): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    open fun isShowBottomMenu() = false

    protected abstract fun layoutId(): Int
}