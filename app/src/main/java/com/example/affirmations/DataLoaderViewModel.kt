package com.example.affirmations

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.model.AffirmationResources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataLoaderViewModel : ViewModel() {
    private val _affirmationResourcesList: MutableLiveData<List<AffirmationResources>> =
        MutableLiveData()
    val affirmationResourcesList: LiveData<List<AffirmationResources>> = _affirmationResourcesList

    private val _affirmationList: MutableLiveData<List<Affirmation>> = MutableLiveData()
    val affirmationList: LiveData<List<Affirmation>> = _affirmationList
    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _affirmationResourcesList.postValue(
                Datasource().loadAffirmations()
            )
        }
    }

    fun loadAffirmation() {
        viewModelScope.launch {
            _affirmationList.postValue(Datasource().loadAffirmation())

        }
    }


}