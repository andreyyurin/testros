package sad.ru.testros.fragments.cities.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sad.ru.testros.R
import sad.ru.testros.models.WeatherDataFromDB

internal class CitiesHistoryAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<CitiesHistoryViewHolder>() {

    private var data: ArrayList<WeatherDataFromDB> = ArrayList()

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
        holder.city.text = data[position].city
        holder.temp.text = data[position].temp
    }

    fun swapItems(data: List<WeatherDataFromDB>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


}