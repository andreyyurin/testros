package sad.ru.testros.activity.main

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import sad.ru.testros.base.BaseView

interface MainActView : BaseView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showBottomMenu(show: Boolean)
}