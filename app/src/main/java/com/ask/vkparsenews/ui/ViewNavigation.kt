package com.ask.vkparsenews.ui

import com.ask.vkparsenews.domain.entities.EventsModel

interface ViewNavigation {

    //open details events fragment
    fun openEventDetailsFragment(eventsModel: EventsModel)

}