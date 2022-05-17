package com.ask.vkparsenews.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ask.vkparsenews.data.model.EventsModel

object MoviesComparator : DiffUtil.ItemCallback<EventsModel>() {

    override fun areItemsTheSame(oldItem: EventsModel, newItem: EventsModel): Boolean {
        return oldItem.idEv == newItem.idEv
    }

    override fun areContentsTheSame(oldItem: EventsModel, newItem: EventsModel): Boolean {
        return oldItem.id == newItem.id || oldItem.title == newItem.title
    }

}