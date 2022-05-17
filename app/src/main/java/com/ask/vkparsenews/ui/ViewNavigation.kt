package com.ask.vkparsenews.ui

import com.ask.vkparsenews.domain.entities.EventsModel

interface ViewNavigation {

    fun openEventsFragment()

    //open details events fragment
    fun openEventDetailsFragment(eventsModel: EventsModel)

}