package es.vass.brastlewark.ui.brastlewark.gnomelist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.vass.brastlewark.R
import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel

class GnomeListAdapter(
    context: Context,
    private val dataSet: ArrayList<GnomeModel>,
    private val itemGnomeClickListener: ItemGnomeClickListener
) : RecyclerView.Adapter<GnomeViewHolder>() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    interface ItemGnomeClickListener {
        fun onItemThingClick(gnomeModel: GnomeModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GnomeViewHolder {
        val view = layoutInflater.inflate(R.layout.recyclerview_item_brastlewark_gnome, parent, false)
        return GnomeViewHolder(view, itemGnomeClickListener)
    }

    override fun onBindViewHolder(holder: GnomeViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun refreshGnomes(gnomeList: ArrayList<GnomeModel>) {
        dataSet.clear()
        dataSet.addAll(gnomeList)
        notifyDataSetChanged()
    }

}