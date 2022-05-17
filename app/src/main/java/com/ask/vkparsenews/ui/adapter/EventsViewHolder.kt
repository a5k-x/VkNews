package com.ask.vkparsenews.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ask.vkparsenews.databinding.ItemEventBinding
import com.ask.vkparsenews.domain.entities.EventsModel

class EventsViewHolder(
    private val vb: ItemEventBinding,
    private val onClick: (EventsModel) -> Unit
) : RecyclerView.ViewHolder(vb.root) {

    fun bind(entity: EventsModel?) {
        vb.titleEvent.text = entity?.title
        vb.timeCreateEvent.text = entity?.data

        if (entity?.url == null) {
            vb.photoEvent.visibility = View.GONE
        } else {
            vb.photoEvent.visibility = View.VISIBLE
            vb.photoEvent.load(entity.url) {
                transformations(CircleCropTransformation())
            }
        }

        vb.moreEvent.setOnClickListener {
            if (entity != null) {
                onClick(entity)
            }
        }
    }
}