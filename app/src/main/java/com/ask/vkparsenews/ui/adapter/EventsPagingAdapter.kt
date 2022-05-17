package com.ask.vkparsenews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

import com.ask.vkparsenews.databinding.ItemEventBinding
import com.ask.vkparsenews.domain.entities.EventsModel

class EventsPagingAdapter(
    diffCallback: DiffUtil.ItemCallback<EventsModel>,
    private val onClick: (EventsModel) -> Unit
) :
    PagingDataAdapter<EventsModel, EventsViewHolder>(diffCallback = diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder =
        EventsViewHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick
        )

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

