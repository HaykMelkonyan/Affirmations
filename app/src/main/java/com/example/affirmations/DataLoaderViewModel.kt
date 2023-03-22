package com.example.affirmations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataLoaderViewModel : ViewModel() {
    private val _affirmationList: MutableLiveData<List<Affirmation>> = MutableLiveData()
    val affirmationList: LiveData<List<Affirmation>> = _affirmationList
    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _affirmationList.postValue(Datasource().loadAffirmations())
        }
    }


}