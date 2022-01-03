package com.example.serverconnection.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.serverconnection.network.MarsProperty


class DetailViewModel(marsProperty: MarsProperty, app: Application) : AndroidViewModel(app) {
    val _selected_property= MutableLiveData<MarsProperty>()
    val selected_property: LiveData<MarsProperty>
        get() = _selected_property
    init {
        _selected_property.value=marsProperty
    }
}
