package com.ask.vkparsenews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.ask.vkparsenews.databinding.FragmentEventDetailsBinding
import com.ask.vkparsenews.domain.entities.EventsModel


class EventDetailsFragment : Fragment() {

    companion object {
        private const val ARG_PARAM_EVENT = "EVENT"

        @JvmStatic
        fun newInstance(eventParam: EventsModel) =
            EventDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_EVENT, eventParam)

                }
            }
    }

    private var eventParam: EventsModel? = null
    private var vb: FragmentEventDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            eventParam = it.getParcelable<EventsModel>(ARG_PARAM_EVENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val event = arguments?.get(ARG_PARAM_EVENT) as EventsModel
        initfields(event)

    }

    private fun initfields(event: EventsModel) {
        if (event?.url == null) {
            vb?.imageEventDetails?.visibility = View.GONE
        } else {
            vb?.imageEventDetails?.visibility = View.VISIBLE
            vb?.imageEventDetails?.load(event.url) {
                transformations(CircleCropTransformation())
            }
        }
        vb?.nameAuthorEvent?.text = event.title
        vb?.descriptionEvent?.text = event.descript
        vb?.timeCreateEvent?.text = event.data
    }
}