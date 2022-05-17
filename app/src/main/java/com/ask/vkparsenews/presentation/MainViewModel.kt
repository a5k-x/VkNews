package com.ask.vkparsenews.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ask.vkparsenews.App
import com.ask.vkparsenews.domain.MainInteractor
import com.ask.vkparsenews.domain.Repository
import com.ask.vkparsenews.domain.entities.EventsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {

    private var liveDataInitStartTimeView: MutableLiveData<String> = MutableLiveData()
    val _liveDataStartTimeView = liveDataInitStartTimeView

    private var liveDataInitEndTimeView: MutableLiveData<String> = MutableLiveData()
    val _liveDataEndTimeView = liveDataInitEndTimeView

    @Inject
    lateinit var interactor: MainInteractor

    @Inject
    lateinit var repository: Repository

    init {
        App.instance.appComponent.inject(this)
    }

    fun initStartTimeView(timeStamp: Long) {
        viewModelScope.launch {
            liveDataInitStartTimeView.postValue(getTimeStamp(timeStamp))
        }
    }

    fun initEndTimeView(timeStamp: Long) {
        viewModelScope.launch {
            liveDataInitEndTimeView.postValue(getTimeStamp(timeStamp))
        }
    }

    private fun getTimeStamp(timeStamp: Long) = interactor.initTimeView(timeStamp)

    suspend fun initflow(startData: String, endData: String): Flow<PagingData<EventsModel>> =
        interactor.initFlow(startData, endData).cachedIn(viewModelScope)


}

