package com.acidapps.beerapp.ui.brewerylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.acidapps.beerapp.data.Brewery
import com.acidapps.beerapp.data.IBreweryRepository

class BreweryListViewModel(private val breweryRepository: IBreweryRepository) : ViewModel() {
    fun getBreweries(): LiveData<List<Brewery>> = breweryRepository.retrieveBreweries()
}