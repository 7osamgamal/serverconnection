package com.example.serverconnection.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.serverconnection.network.MarsApi
import com.example.serverconnection.network.MarsApiFilter
import com.example.serverconnection.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class marsApiStatus { LOADING, ERROR, DONE }
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<marsApiStatus>()
    val status: LiveData<marsApiStatus>
        get() = _status

    private val _navigate_to_selected_property = MutableLiveData<MarsProperty>()
    val navigate_to_selected_property: LiveData<MarsProperty>
        get() = _navigate_to_selected_property

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties(filter:MarsApiFilter) {
        coroutineScope.launch {
            val getProperitesDeferred = MarsApi.retrofitservice.getProperites(filter.value)

            try {
                _status.value = marsApiStatus.LOADING
                val ListResult = getProperitesDeferred.await()
                _status.value = marsApiStatus.DONE
                if (ListResult.size > 0) {
                    _properties.value = ListResult
                }
            } catch (t: Throwable) {
                _status.value = marsApiStatus.ERROR
                _properties.value = ArrayList()

            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    fun displayPropertyDetails(marsProperty: MarsProperty)
    {
        _navigate_to_selected_property.value=marsProperty
    }
    fun displayPropertyDetailsComplete()
    {
        _navigate_to_selected_property.value=null
    }
    fun updatefilter(filter: MarsApiFilter){
        getMarsRealEstateProperties(filter)
    }
}
