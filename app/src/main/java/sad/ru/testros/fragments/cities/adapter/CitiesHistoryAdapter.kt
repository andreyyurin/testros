package sad.ru.testros.fragments.cities.adapter

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sad.ru.testros.R
import sad.ru.testros.models.WeatherDataFromDB

internal class CitiesHistoryAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<CitiesHistoryViewHolder>() {

    private var data: ArrayList<WeatherDataFromDB> = ArrayList()
    private lateinit var popUp: PopupWindow
    private lateinit var inflater: LayoutInflater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesHistoryViewHolder {
        return CitiesHistoryViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_history,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CitiesHistoryViewHolder, position: Int) {
        initData(holder, position)
        initPopup()
        initClickListener(holder, position)
    }

    private fun initClickListener(holder: CitiesHistoryViewHolder, position: Int) {
        holder.layout.setOnClickListener {
            setDataToPopup(position)
            showPopup(holder)
        }
    }

    private fun setDataToPopup(position: Int) {
        popUp.contentView.findViewById<TextView>(R.id.popup_name).text = data[position].city
        popUp.contentView.findViewById<TextView>(R.id.popup_cloud).text = data[position].clouds
        popUp.contentView.findViewById<TextView>(R.id.popup_wet).text = data[position].wet
        popUp.contentView.findViewById<TextView>(R.id.popup_date).text = data[position].date
        popUp.contentView.findViewById<TextView>(R.id.popup_temp).text = data[position].temp
    }

    private fun showPopup(holder: CitiesHistoryViewHolder) {
        popUp.showAtLocation(
            holder.layout,
            Gravity.CENTER,
            0,
            0
        )
    }

    private fun initData(holder: CitiesHistoryViewHolder, position: Int) {
        holder.city.text = data[position].city
        holder.temp.text = data[position].temp
    }

    private fun initPopup() {
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        popUp = PopupWindow(
            inflater.inflate(R.layout.popup_weather_data, null, false),
            convertDpToPx(300f, context).toInt(),
            convertDpToPx(300f, context).toInt(),
            true
        )
        popUp.elevation = 10f
    }

    fun convertDpToPx(dp: Float, context: Context?): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    fun swapItems(data: List<WeatherDataFromDB>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


}