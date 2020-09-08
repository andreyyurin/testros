package sad.ru.testros.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.coroutines.Dispatchers
import sad.ru.testros.retrofit.RetrofitClientInstance
import sad.ru.testros.retrofit.RetrofitService
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment(protected val uiContext: CoroutineContext = Dispatchers.Main) :
    MvpAppCompatFragment(), BaseView {

    lateinit var service: RetrofitService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service =
            RetrofitClientInstance.retrofitInstance!!.create(RetrofitService::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    open fun isShowBottomMenu() = false

    protected abstract fun layoutId(): Int
}