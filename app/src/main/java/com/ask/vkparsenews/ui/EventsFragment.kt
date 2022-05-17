package com.ask.vkparsenews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ask.vkparsenews.databinding.FragmentEventsBinding
import com.ask.vkparsenews.domain.entities.EventsModel
import com.ask.vkparsenews.presentation.MainViewModel
import com.ask.vkparsenews.ui.adapter.EventsPagingAdapter
import com.ask.vkparsenews.ui.adapter.MoviesComparator
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.sql.Timestamp

class EventsFragment : Fragment() {

    private var vb: FragmentEventsBinding? = null
    private  val eventsPagingDataAdapter = EventsPagingAdapter(MoviesComparator, ::openDetailsFragment)
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentEventsBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservable()
        initTimeListener()

    }

    private fun initRecyclerView() {
        vb?.recyclerViewContainer?.adapter = eventsPagingDataAdapter
    }

    private fun openDetailsFragment(event: EventsModel){
        (activity as ViewNavigation).openEventDetailsFragment(event)
    }

    private fun initObservable() {
        mainViewModel._liveDataStartTimeView.observe(viewLifecycleOwner, ::renderStartTimeView)
        mainViewModel._liveDataEndTimeView.observe(viewLifecycleOwner, ::renderEndTimeView)
    }

    //initialization start time on TextView (start time = YESTERDAY_TIME_MSEC)
    private fun initTimeOnView() {
        mainViewModel.initStartTimeView(Timestamp(System.currentTimeMillis()).time - YESTERDAY_TIME_MSEC)
        mainViewModel.initEndTimeView(Timestamp(System.currentTimeMillis()).time)

    }

    private fun renderStartTimeView(timeStamp: String) {
        vb?.startDate?.text = timeStamp
    }

    private fun renderEndTimeView(timeStamp: String) {
        vb?.endDate?.text = timeStamp
    }

    private fun initTimeListener() {
        initTimeOnView()
        vb?.startDate?.setOnClickListener {
            startDataPicker()
            vb?.startDate?.doAfterTextChanged {
                getListNews()
            }
        }
        vb?.endDate?.setOnClickListener {
            endDataPicker()
            vb?.endDate?.doAfterTextChanged {
                getListNews()
            }
        }
         getListNews()
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun getListNews() {

        val startDate = vb?.startDate?.text.toString()
        val endDate = vb?.endDate?.text.toString()

        lifecycleScope.launch {
            mainViewModel.initflow(startDate, endDate).collectLatest { value ->
                eventsPagingDataAdapter.submitData(lifecycle,value)
            }
        }

    }

    private fun startDataPicker() {
        val datePicker = getDataPicker("Start")
        datePicker.addOnPositiveButtonClickListener {
            mainViewModel.initStartTimeView(it)
        }
        datePicker.show(childFragmentManager, "")
    }

    private fun endDataPicker() {
        val datePicker = getDataPicker("End")
        datePicker.addOnPositiveButtonClickListener {
            mainViewModel.initEndTimeView(it)
        }
        datePicker.show(childFragmentManager, "")
    }

    private fun getDataPicker(text: String): MaterialDatePicker<Long> =
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select $text date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

    companion object {
        private const val YESTERDAY_TIME_MSEC = 86_400_000
        fun newInstance() = EventsFragment()

    }
}