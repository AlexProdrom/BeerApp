package com.acidapps.beerapp.ui.brewerylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.acidapps.beerapp.data.IBreweryRepository
import kotlinx.coroutines.Dispatchers

class BreweryListViewModel(private val breweryRepository: IBreweryRepository) : ViewModel() {
    val breweries = liveData(Dispatchers.IO) {
        val breweries = breweryRepository.retrieveBreweries()
        emit(breweries)
    }
}