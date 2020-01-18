package com.acidapps.beerapp.ui.brewerylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.acidapps.beerapp.data.Brewery
import com.acidapps.beerapp.data.BreweryRepository

class BreweryListViewModel(private val breweryRepository: BreweryRepository) : ViewModel() {
    fun getBreweries(): LiveData<List<Brewery>> = breweryRepository.retrieveBreweries()
}