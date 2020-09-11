package sad.ru.testros.fragments.cities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_cities.*
import sad.ru.testros.R
import sad.ru.testros.base.BaseFragment
import sad.ru.testros.db.HistoryDbHelper
import sad.ru.testros.fragments.cities.adapter.CitiesHistoryAdapter
import sad.ru.testros.models.WeatherDataFromDB

class CitiesFragment : BaseFragment(), CitiesView {
    override fun layoutId(): Int = R.layout.fragment_cities

    @InjectPresenter
    lateinit var presenter: CitiesPresenter

    override fun isShowBottomMenu(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        loadData()
    }

    private fun initRecycler() {
        recycler_history.layoutManager = LinearLayoutManager(this.requireContext())
        recycler_history.adapter = adapter
    }

    private fun loadData() {
        val cursor = dbHelper.getAllData()
        cursor!!.moveToFirst()

        val listOfData = ArrayList<WeatherDataFromDB>()
        while (cursor.moveToNext()) {
            listOfData.add(
                WeatherDataFromDB(
                    cursor.getString(cursor.getColumnIndex(HistoryDbHelper.COLUMN_CITY)),
                    cursor.getString(cursor.getColumnIndex(HistoryDbHelper.COLUMN_WET)),
                    cursor.getString(cursor.getColumnIndex(HistoryDbHelper.COLUMN_CLOUD)),
                    cursor.getString(cursor.getColumnIndex(HistoryDbHelper.COLUMN_TEMPERATURE)),
                    cursor.getString(cursor.getColumnIndex(HistoryDbHelper.COLUMN_DATE))
                )
            )
        }

        cursor.close()

        adapter.swapItems(listOfData)
    }

    private val adapter: CitiesHistoryAdapter by lazy {
        CitiesHistoryAdapter(this.requireContext())
    }
}