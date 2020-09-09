package sad.ru.testros.base

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.terrakok.cicerone.Router
import sad.ru.testros.MainApplication
import sad.ru.testros.retrofit.RetrofitClientInstance
import sad.ru.testros.retrofit.RetrofitService
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<View : BaseView>(protected val uiContext: CoroutineContext = Dispatchers.Main) :
    MvpPresenter<View>(), CoroutineScope {

    var router: Router? = MainApplication.INSTANCE.router


    val service: RetrofitService =
        RetrofitClientInstance.retrofitInstance!!.create(RetrofitService::class.java)
    private val job = Job()
    override val coroutineContext = job + Dispatchers.Main + SupervisorJob()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun <obj : Any?> loadData(needFunc: Call<obj>, setData: (obj) -> Unit) {
        val call: Call<obj> = needFunc
        call.enqueue(object : Callback<obj> {
            override fun onFailure(call: Call<obj>, t: Throwable) {
                Log.d("http-test", ":err:" + call.toString() + " " + t.localizedMessage)
            }

            override fun onResponse(call: Call<obj>, response: Response<obj>) {
                setData.invoke(response.body()!!)
            }
        })
    }

    protected fun withExceptionHandler() = CoroutineExceptionHandler { _, t ->
        {
            t.printStackTrace()
            showErrorOrSomething(t)
        }
    }

    private fun showErrorOrSomething(t: Throwable) {
        Log.e("http-error", "err:" + t.localizedMessage)
    }
}