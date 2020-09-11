package sad.ru.testros.fragments.cities.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*

internal class CitiesHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val city: TextView = view.tv_name
    val temp: TextView = view.tv_temp
}