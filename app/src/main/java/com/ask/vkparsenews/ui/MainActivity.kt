package com.ask.vkparsenews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ask.vkparsenews.R
import com.ask.vkparsenews.domain.entities.EventsModel

class MainActivity : AppCompatActivity(), ViewNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openEventsFragment()
    }

    override fun openEventsFragment(){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container,EventsFragment.newInstance())
            .commit()
    }

    override fun openEventDetailsFragment(eventsModel: EventsModel) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, EventDetailsFragment.newInstance(eventsModel))
            .commit()
    }
}