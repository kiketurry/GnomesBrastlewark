package es.vass.brastlewark.ui.brastlewark.gnomelist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import es.vass.brastlewark.R
import es.vass.brastlewark.data.domain.model.brastlewark.GnomeModel
import es.vass.brastlewark.databinding.RecyclerviewItemBrastlewarkGnomeBinding
import es.vass.brastlewark.extensions.glideWithHeaders
import es.vass.brastlewark.ui.brastlewark.gnomelist.adapter.GnomeListAdapter.ItemGnomeClickListener


class GnomeViewHolder(var view: View, private val gnomeClickListener: ItemGnomeClickListener) : RecyclerView.ViewHolder(view) {
    private val binding = RecyclerviewItemBrastlewarkGnomeBinding.bind(view)

    fun bind(gnomeModel: GnomeModel) {
        binding.ivImage.glideWithHeaders(view.context, gnomeModel.thumbnail)
        binding.tvName.text = gnomeModel.name
        view.setOnClickListener { gnomeClickListener.onItemThingClick(gnomeModel) }
    }
}